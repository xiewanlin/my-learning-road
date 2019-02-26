package com.xwl.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class BioServerHandler implements Runnable {

	private Socket socket;

	public BioServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			// 服务器接收数据
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String body = null;
			body = reader.readLine();
			System.out.println("server服务器接收参数：" + body);

			// 服务器回复
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println("服务器收到了数据并回复！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (socket != null) {
					socket.close();
					socket = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
