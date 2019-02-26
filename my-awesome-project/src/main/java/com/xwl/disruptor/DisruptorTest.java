package com.xwl.disruptor;

import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorTest {
	
	/**
	 * @author xiewanlin
	 *	消息类时间
	 */
	public static class MessageEvent{
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	/**
	 * @author xiewanlin
	 *	消息时间工厂
	 */
	public static class MessageEventFactory implements EventFactory<MessageEvent>{

		@Override
		public MessageEvent newInstance() {
			return new MessageEvent();
		}
	}
	
	/**
	 * @author xiewanlin
	 *	消息装换类，负责将消息转为时间
	 */
	public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent, String>{

		@Override
		public void translateTo(MessageEvent messageEvent, long l, String message) {
			messageEvent.setMessage(message);
		}
	}
	
	/**
	 * @author xiewanlin
	 *	消费者线程工厂类
	 */
	public static class MessageThreadFactory implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "Simple Disruptor Test Thread!");
		}
	}
	
	/**
	 * @author xiewanlin
	 *	消息处理类
	 */
	public static class MessageEventHandler implements EventHandler<MessageEvent>{

		@Override
		public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
			//单纯打印消息
			System.out.println(messageEvent.getMessage());
		}
	}
	
	/**
	 * @author xiewanlin
	 *	异常处理类
	 */
	public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent>{

		@Override
		public void handleEventException(Throwable throwable, long l, MessageEvent messageEvent) {
			throwable.printStackTrace();
		}

		@Override
		public void handleOnShutdownException(Throwable throwable) {
			throwable.printStackTrace();
		}

		@Override
		public void handleOnStartException(Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	/**
	 * @author xiewanlin
	 *	消息生产者
	 */
	public static class MessageEventProducer{
		private RingBuffer<MessageEvent> ringBuffer;
		
		public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
			this.ringBuffer = ringBuffer;
		}
		
		//将消息输出到ringBuffer
		public void onData(String message){
			EventTranslatorOneArg<MessageEvent, String> translator = new MessageEventTranslator();
			ringBuffer.publishEvent(translator, message);
		}
	}
	
	public static void main(String[] args) {
		String message = "Hello Disruptor!";
		int ringBufferSize = 1024; //必须是2的N次方
		
		Disruptor<MessageEvent> disruptor = new Disruptor<>(new MessageEventFactory(), ringBufferSize, new MessageThreadFactory(),
				ProducerType.SINGLE, new BlockingWaitStrategy());
		disruptor.handleEventsWith(new MessageEventHandler());
		disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
		
		RingBuffer<MessageEvent> ringBuffer = disruptor.start();
		MessageEventProducer producer = new MessageEventProducer(ringBuffer);
		producer.onData(message);

	}

}
