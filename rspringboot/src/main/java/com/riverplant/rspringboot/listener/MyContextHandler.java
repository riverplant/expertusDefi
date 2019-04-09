package com.riverplant.rspringboot.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyContextHandler {
    @EventListener
	public void event(ContextClosedEvent event) {
    	
		System.out.println("context 关闭时间为:"+event.getTimestamp());
	}
    
    @EventListener
   	public void event2(ContextStoppedEvent event) {
       	
   		System.out.println("context 停止时间为:"+event.getTimestamp());
   	}
}
