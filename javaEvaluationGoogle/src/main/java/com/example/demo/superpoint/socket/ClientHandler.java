package com.example.demo.superpoint.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final RequestHandler requestHandler;   
	public ClientHandler(Socket clientSocket, RequestHandler requestHandler) {
		this.clientSocket = clientSocket;
		this.requestHandler = requestHandler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (Scanner input = new Scanner(clientSocket.getInputStream())) {// 通过Scanner读取输入内容) {
			while (true) {
				//等待点
				String request = input.nextLine();//等待用户输入下一行
				//当用户输入quit，就关闭服务器
				if(request.equalsIgnoreCase("quit")) {
					break;
				}
				// %s:clientSocket.getRemoteSocketAddress()
				// %s:request
				System.out.println(
						String.format("Request from %s: %s", clientSocket.getRemoteSocketAddress(), request));
				String response = requestHandler.handler(request);
				clientSocket.getOutputStream().write(response.getBytes());
			}
		}catch (IOException e) {
			System.out.println("Caught exception:" + e);
			throw new RuntimeException(e);
		}
	}

}
