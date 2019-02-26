package com.xwl.io.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AioServer {
	private ExecutorService executorService;
	private AsynchronousChannelGroup threadGroup;
	//服务通道
	public AsynchronousServerSocketChannel asynServerSocketChannel;
	
	public void start(int port){
		try {
			//1.创建线程池
			executorService = Executors.newCachedThreadPool();
			//2.创建通道组
			threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
			//3.创建服务通道
			asynServerSocketChannel = AsynchronousServerSocketChannel.open(threadGroup);
			//4.进行绑定
			asynServerSocketChannel.bind(new InetSocketAddress(port));
			System.out.println("AIO server start...");
			//5.等待客户请求
			asynServerSocketChannel.accept(this, new AioServerHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AioServer server = new AioServer();
		server.start(8888);
	}
	
}
