package com.example.boot.spring4.beans.factory;

import org.springframework.beans.factory.FactoryBean;
/**
 * FactoryBean:该class主要有用于创建其它的Bean
 * @author riverplant
 *
 */
public class RunnableFactoryBean implements FactoryBean<Runnable> {

	@Override//获取到RunnableFactorBean创建的实例对象
	public Runnable getObject() throws Exception {
		// 创建一个Runnable
		return ()->{};
	}

	@Override//获取到创建对象的类型
	public Class<?> getObjectType() {
		
		return Runnable.class;
	}
   /**
    * 是否单例
    */
	public boolean isSingleton() {
		return false;
	}
}
