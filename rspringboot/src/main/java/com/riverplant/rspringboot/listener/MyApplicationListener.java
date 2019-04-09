package com.riverplant.rspringboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.riverplant.rspringboot.events.MyApplicationEvents;
/**
 * 自定义监听器，监听事件为自定义事件
 * @author riverplant
 */
@Component//可以通过注解来添加监听器
public class MyApplicationListener implements ApplicationListener<MyApplicationEvents> {

	@Override
	public void onApplicationEvent(MyApplicationEvents event) {
		System.out.println("接收到事件:"+event.getClass());
	}

}
