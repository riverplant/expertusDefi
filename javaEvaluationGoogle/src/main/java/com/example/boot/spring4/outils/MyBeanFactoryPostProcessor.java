package com.example.boot.spring4.outils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
/**
 * 
 * @author riverplant
 *该类主要用于初始化容器获得BeanFactory，通过该类可以获得容器中的bean
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		
		beanFactory.getBeanDefinitionCount();//获得当前容器中有多少个bean

	}

}
