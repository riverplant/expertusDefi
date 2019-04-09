package com.example.demo.actimq;

import javax.jms.ConnectionFactory;

/**
 * 
 * @author riverplant 
 * 1.p2p 队列模式
 * 2.发布订阅 J
 * MS 编码接口 ConnectionFactory 创建连接到消息中间件的连接工厂
 * Connection 应用程序和消息服务器之间的通信链路 Destination 消息发布和接收的地点，包括队列或主题 Session
 * 单线程的上下文，用于发送和接收消息 MessageConsumer(消费者) 由会话创建，用于接收发送到目标的消息
 * MessageProducer(生产者) :由会话创建，用于发送消息到目标 Message:
 * 在消费者和生产者之间传送的对象，消息头，一组消息属性，一个消息体
 * 
 * ConnectionFactory ---(Create)--->Connection---(Create)--->Session
 * MessageConsumer(消费者)
 * <---(Create)---Session---(Create)--->MessageProducer(生产者)
 * Session---(Create)--->Message MessageProducer---Send to-->Destination
 *  MessageConsumer---Receive from-->Destination
 */
public class MyActiveMQ {
    private ConnectionFactory connectionFactory;
	public void p2p() {
		
	}
}
