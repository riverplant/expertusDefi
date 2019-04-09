package com.example.demo.actimq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.controller.FileController;

/**
 * 生产者
 * 
 * @author riverplant
 *
 */
public class AppProducer {
	private static final String url = "tcp://127.0.0.1:61616";
	private static final String queueName = "queue-test";
	private static final Logger logger = LoggerFactory.getLogger(AppProducer.class);

	public static void main(String[] args) {
		// 1.创建ConnectionFacotry,该接口被ActiveMQConnectionFactory来实现
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		/**
		 * public class ActiveMQConnectionFactory extends JNDIBaseStorable implements
		 * ConnectionFactory, QueueConnectionFactory, TopicConnectionFactory,
		 * StatsCapable, Cloneable
		 */
		// 2.创建连接Connection
		try (Connection connection = connectionFactory.createConnection();) {
			// 3.启动连接，物理连接
			connection.start();
			// 4.创建会话
			/**
			 * Session javax.jms.Connection.createSession(boolean transacted, int
			 * acknowledgeMode) throws JMSException boolean transacted:是否在事务中处理 int
			 * acknowledgeMode:应答模式
			 */
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 5.创建一个目标
			Destination destination = session.createQueue(queueName);
			// 6.创建一个生产者
			MessageProducer producer = session.createProducer(destination);

			for (int i = 0; i < 100; i++) {
				// 7.创建消息
				TextMessage message = session.createTextMessage("test:" + i);
				// 8.用生产者发送消息
				producer.send(destination, message);
				logger.info("发送消息" + message.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
