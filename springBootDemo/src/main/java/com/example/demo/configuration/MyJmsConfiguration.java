package com.example.demo.configuration;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jms-activeMQ队列设置
 * @author riverplant
 *
 */
@Configuration
public class MyJmsConfiguration {
	@Bean
    public Queue queue() {
    	return new ActiveMQQueue("river.queue");
    }
}
