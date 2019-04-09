package com.example.demo.component;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author riverplant
 *
 */
@Component
public class MyJmsComponent {
    @Autowired(required=true)
	private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    
    public void send(String msg) {
    	this.jmsMessagingTemplate.convertAndSend(this.queue,msg);
    }
    @JmsListener(destination = "river.queue")
    public void receiveQueue(String text) {
    	System.out.println("接收到:" + text);
    }
}
