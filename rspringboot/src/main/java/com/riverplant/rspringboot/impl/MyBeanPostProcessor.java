package com.riverplant.rspringboot.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	private List<String> packages;
	
	public List<String> getPackages() {
		return packages;
	}

	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		for(String pack:packages) {
			if(bean.getClass().getPackage().getName().equalsIgnoreCase(pack)) {
				System.out.println("该bean的名字是:"+bean.getClass()+";输入"+pack+"包");
			}
		}
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
