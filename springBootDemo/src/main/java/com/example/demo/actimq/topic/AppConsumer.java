package com.example.demo.actimq.topic;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消费者,需要先订阅消息！！
 * @author riverplant
 *
 */
public class AppConsumer {
	private static final String url = "tcp://127.0.0.1:61616";
	private static final String topicName = "topic-test";
private static final Logger logger = LoggerFactory.getLogger(AppConsumer.class);
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
			// 5.创建一个主题目标
			Destination destination = session.createTopic(topicName);
			// 6.创建一个消费者
			MessageConsumer consumer = session.createConsumer(destination);
            //7.监听消费信息,创建一个监听器
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					TextMessage tmessage = (TextMessage) message;
					try {
						logger.info("接收到消息"+tmessage.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
