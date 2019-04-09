package com.example.demo.superpoint.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
/**
 * NIO: new IO
 * @author riverplant
 *
 */
public class NioServer {

	public static void main(String[] args) {
		//使用ServerSocketChannel
		try(ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
			//异步操作中所有的操作都不会block
			serverChannel.configureBlocking(false);
			serverChannel.bind(new InetSocketAddress(8888));
			System.out.println("Listening on" + serverChannel.getLocalAddress());
			Selector selector = Selector.open();
			//将selector注册serverChannel，设置对SelectionKey.OP_ACCEPT感兴趣
			//如果serverChannel可以被accept,会被selector汇报出来
			//如果没有设置SelectionKey.OP_ACCEPT，将不会被汇报
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			while(true) {
				int selected = selector.select();//获得当前的连接数
				if(selected == 0 ) {
					continue;
				}
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iter = selectedKeys.iterator();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				RequestHandler requestHandler = new RequestHandler();
				while(iter.hasNext()) {
					SelectionKey key = iter.next();
					if(key.isAcceptable()) {//如果已连接
						ServerSocketChannel channel = (ServerSocketChannel) key.channel();
						SocketChannel clientChannel = channel.accept();
						System.out.println("Accepting incoming connection from" + clientChannel.getLocalAddress());
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					}//acceptable
					
					if(key.isReadable()) {//如果可读
						SocketChannel channel = (SocketChannel) key.channel();
						channel.read(buffer);
						String request = new String(buffer.array()).trim();//获得客户端的请求
						if(request.equalsIgnoreCase("quit")) {
							break;
						}
						System.out.println(
								String.format("Request from %s: %s", channel.getRemoteAddress(), request));
						buffer.clear();
						String response = requestHandler.handler(request);//处理客户端传过来的请求
						channel.write(ByteBuffer.wrap(response.getBytes()));//写出给客户端
					}//readable	
					iter.remove();//删除当前已经处理的channel
					
				}//while(iter.hasNext())
				
			}//while
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
