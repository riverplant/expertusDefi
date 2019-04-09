package com.example.boot.spring4.outils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.boot.spring4.interfaces.MyContextAware;
/**
 * EchoBeanPostProcessor会在每一个bean初始化的时候调用一次
 * bean需要的参数(依赖装配)init--->postProcessBeforeInitialization-->bean init --->postProcessAfterInitialization
 * @author riverplant
 *
 */
@Component
public class EchoBeanPostProcessor implements BeanPostProcessor {
	@Autowired
	private ApplicationContext applicationContext;
    /**
     * 如果返回null该对象将无法从容器中获取
     */
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("==========postProcessBeforeInitialization=============");
		if(bean instanceof MyContextAware ) {
			MyContextAware myContextAware = (MyContextAware) bean;
			myContextAware.setApplicationContext(applicationContext);
		}
		return bean;
	}
	 /**
     * 如果返回null该对象将无法从容器中获取
     */
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("==========postProcessAfterInitialization=============");
		return bean;
	}
}
