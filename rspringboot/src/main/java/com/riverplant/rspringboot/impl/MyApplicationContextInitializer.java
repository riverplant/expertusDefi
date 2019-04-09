package com.riverplant.rspringboot.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * 
 * @author riverplant
 *
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
	    System.out.println("当前容器中有"+applicationContext.getBeanDefinitionCount()+"个Bean");
	    ConfigurableListableBeanFactory  beanFactory = applicationContext.getBeanFactory();
	    /**
	     * BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(MyBeanPostProcessor.class);
		 bdb.addPropertyValue("packages", packages);//通过该方法给MyBeanPostProcessor中的packages属性初始化
		 BeanDefinition beanDefinition = bdb.getBeanDefinition();
		 registry.registerBeanDefinition("myBeanPostProcessor", beanDefinition);
	     */
	    //BeanDefinition  bd = beanFactory.getBeanDefinition("User");
	    //System.out.println("通过BeanDefinition获得的bean是"+bd.getBeanClassName());
	}

}
