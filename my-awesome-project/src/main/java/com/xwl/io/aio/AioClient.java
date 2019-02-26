package com.xwl.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AioClient implements Runnable{

	private static int PORT = 8888;
	private static String IP = "127.0.0.1";
	private AsynchronousSocketChannel asynSocketChannel;
	
	public AioClient() throws Exception{
		asynSocketChannel = AsynchronousSocketChannel.open();
	}
	
	public void connect(){
		asynSocketChannel.connect(new InetSocketAddress(IP, PORT));
	}
	
	public void write(String request){
		try {
			asynSocketChannel.write(ByteBuffer.wrap(request.getBytes())).get();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			asynSocketChannel.read(byteBuffer).get();
			byteBuffer.flip();
			byte[] respBuf = new byte[byteBuffer.remaining()];
			byteBuffer.get(respBuf);
			System.out.println("客户端收到服务器返回："+new String(respBuf, "utf-8").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			AioClient client = new AioClient();
			client.connect();
			new Thread(client);
			client.write("start aio client");
		}
	}

}
