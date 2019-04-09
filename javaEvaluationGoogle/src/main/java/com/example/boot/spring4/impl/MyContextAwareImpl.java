package com.example.boot.spring4.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.boot.spring4.interfaces.MyContextAware;
/**
 * 自定义接口MyContextAware的实现
 * @author riverplant
 *
 */
@Component//通过
public class MyContextAwareImpl implements MyContextAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	@Override
	public String toString() {
		return "MyContextAwareImpl [applicationContext=" + applicationContext + "]";
	}

	
}
