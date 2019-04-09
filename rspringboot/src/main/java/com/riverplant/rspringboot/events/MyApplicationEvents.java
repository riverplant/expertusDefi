package com.riverplant.rspringboot.events;

import org.springframework.context.ApplicationEvent;
/**
 * 自定义监听的事件
 * @author riverplant
 *
 */
public class MyApplicationEvents extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public MyApplicationEvents(Object source) {
		super(source);
		
	}

}
