package com.example.demo.superpoint.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSever {

	public static void main(String[] args) {
		ExecutorService executorService = 
				Executors.newFixedThreadPool(3);
		RequestHandler requestHandler = new RequestHandler();
		try (ServerSocket serverSocket = new ServerSocket(6666)) {
			System.out.println("Listening on" + serverSocket.getLocalSocketAddress());
			while (true) {
				//等待点
				Socket clientSocket = serverSocket.accept();// 接收客户端连接
				System.out.println("Accepting incoming connection from" + clientSocket.getRemoteSocketAddress());
				executorService.submit(new ClientHandler(clientSocket, requestHandler));				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
