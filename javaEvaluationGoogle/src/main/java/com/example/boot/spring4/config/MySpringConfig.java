package com.example.boot.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.boot.spring4.beans.Dog;
import com.example.boot.spring4.beans.Jeep;
import com.example.boot.spring4.beans.MyBean;
import com.example.boot.spring4.beans.User;
import com.example.boot.spring4.beans.factory.JeepFactory;
import com.example.boot.spring4.beans.factory.RunnableFactoryBean;

//该类为配置类,里面配置bean
@Configuration
public class MySpringConfig {
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public Dog createDog() {
		return new Dog();
	}

	// 通过创建JeepFactory来创建Jeep
	@Bean
	public JeepFactory createJeepFactory() {
		return new JeepFactory();
	}

	// 创建User
	@Bean
	public User createUser() {
		return new User();
	}

	@Bean // 当创建Jeep的时候，会默认将作为参数的JeepFactory创建
	public Jeep createJeep(JeepFactory jeepFactory) {
		return jeepFactory.createJeep();
	}

	@Bean(name = "mybean") // 配置了一个bean
	@Scope("prototype") // 默认为singleton:多次获取都是拿到同一个对象,现在改为prototype就可以拿到不同的对象了
	public MyBean createMyBean() {
		return new MyBean();
	}

	@Bean
	@Scope("prototype") // 默认为singleton:多次获取都是拿到同一个对象,现在改为prototype就可以拿到不同的对象了
	public RunnableFactoryBean createRunnableFactoryBean() {
		return new RunnableFactoryBean();
	}
}
