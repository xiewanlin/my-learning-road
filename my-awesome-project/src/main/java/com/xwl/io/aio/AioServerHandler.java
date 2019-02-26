package com.xwl.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioServerHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer>{
	
	private final int BUFFER_SIZE = 1024;
	
	@Override
	public void completed(AsynchronousSocketChannel result, AioServer attachment) {
		attachment.asynServerSocketChannel.accept(attachment, this);
		read(result);
	}

	private void read(final AsynchronousSocketChannel asynSocketChannel) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		asynSocketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				attachment.flip();
				//服务器读取数据
				String resultData = new String(attachment.array()).trim();
				System.out.println("aio server收到信息："+resultData);
				//服务器返回数据
				String resp = "aio server 给你返回了。";
				write(asynSocketChannel, resp);
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
		});
	}
	
	private void write(AsynchronousSocketChannel asynSocketChannel, String resp) {
		try {
			ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
			buf.put(resp.getBytes());
			buf.flip();
			asynSocketChannel.write(buf).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void failed(Throwable exc, AioServer attachment) {
		// TODO Auto-generated method stub
		
	}

}
