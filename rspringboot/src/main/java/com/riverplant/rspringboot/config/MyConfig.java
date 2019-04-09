package com.riverplant.rspringboot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootConfiguration
@ConditionalOnProperty(name="runnable.enable",havingValue="true",matchIfMissing=true)//必须配置文件中配置了runnable.enable=true
public class MyConfig {
	@Bean
	public Runnable createRunnalbe() {
		return ()->{System.out.println("======defualtRunnalbe====");};
	}
	
	@Bean
	@ConditionalOnClass(name="com.google.gson.Gson")//当classpath中有Gson类的时候装配
	public Runnable createGsonRunnalbe() {
		return ()->{System.out.println("======GsonRunnalbe====");};
	}
	
	@Bean
	@ConditionalOnBean(name="User")//当有一个bean存在的时候装配
	public Runnable createBeanRunnalbe() {
		return ()->{System.out.println("======BeanRunnalbe====");};
	}
	
	@Bean
	@Profile("dev")//只有当dev配置文件被激活才会注入
	public Runnable createRunnalbeDev() {
		return ()->{System.out.println("======RunnalbeDev====");};
	}
	
	@Bean
	@Profile("test")//只有当test配置文件被激活才会注入
	public Runnable createRunnalbeTest() {
		return ()->{System.out.println("======RunnalbeTest====");};
	}

}
