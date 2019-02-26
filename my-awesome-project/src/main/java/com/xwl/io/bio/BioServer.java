package com.xwl.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class BioServer {
	private static final int PORT = 8888;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		ThreadPoolExecutor executor = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("BIO server服务器启动");
			while(true){
				socket = serverSocket.accept();
				new Thread(new BioServerHandler(socket)).start();
			}
//			executor = new ThreadPoolExecutor(10,100,5000L,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
//			while(true){
//				socket = serverSocket.accept();
//				executor.execute(new BioServerHandler(socket));
//			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (null!=socket) {
					socket.close();
					socket=null;
				}
				if(null!=serverSocket){
					serverSocket.close();
					serverSocket=null;
					System.out.println("BIO server服务器关闭");
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
