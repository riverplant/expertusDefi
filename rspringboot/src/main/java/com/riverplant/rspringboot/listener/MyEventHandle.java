package com.riverplant.rspringboot.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.riverplant.rspringboot.events.MyApplicationEvents;
/**
 * 自定义事件handle
 * @author riverplant
 *
 */
@Component
public class MyEventHandle {
    /**
     * 参数必须是ApplicationEvent或者其子类
     * @param event
     */
	@EventListener//
	public void event(MyApplicationEvents event) {
		System.out.println("MyEventHandle 收到事件: "+event.getClass());
	}
}
