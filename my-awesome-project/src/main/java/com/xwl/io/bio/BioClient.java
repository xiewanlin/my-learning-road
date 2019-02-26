package com.xwl.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class BioClient {
	private static int PORT = 8888;
	private static String IP = "127.0.0.1";
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			clientReq(i);
		}
	}
	private static void clientReq(int i) {
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			socket = new Socket(IP, PORT);
			//客户端发送数据
			String body = "hello world，"+i;
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(body);
			//客户端接收服务器返回数据
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(i+":"+reader.readLine());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
	}
}
