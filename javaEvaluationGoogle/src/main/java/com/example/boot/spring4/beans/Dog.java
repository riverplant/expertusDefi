package com.example.boot.spring4.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog {
   // @PostConstruct//指定初始化
	public void init() {
      System.out.println("========init=========");
	}
   // @PreDestroy//指定销毁
	public void destroy() {
		 System.out.println("========destroy=========");
	}
}
