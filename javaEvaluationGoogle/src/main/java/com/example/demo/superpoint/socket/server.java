package com.example.demo.superpoint.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器端:监听666，客户端发abc，服务端返回hello abc
 * 
 * @author riverplant
 *
 */
public class server {
	private static final Logger LOG = LoggerFactory.getLogger(server.class);

	public static void main(String[] args) {
		RequestHandler requestHandler = new RequestHandler();
		try (ServerSocket serverSocket = new ServerSocket(6666)) {
			System.out.println("Listening on" + serverSocket.getLocalSocketAddress());
			while (true) {
				Socket clientSocket = serverSocket.accept();// 接收客户端连接
				System.out.println("Accepting incoming connection from" + clientSocket.getRemoteSocketAddress());
				new ClientHandler(clientSocket, requestHandler).run();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
