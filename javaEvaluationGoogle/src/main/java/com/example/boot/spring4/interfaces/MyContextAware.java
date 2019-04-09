package com.example.boot.spring4.interfaces;

import org.springframework.context.ApplicationContext;

public interface MyContextAware {

	public void setApplicationContext(ApplicationContext applicationContext);
}
