package com.example.boot.spring4.outils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import com.example.boot.spring4.beans.testBean;
/**
 * 
 * @author riverplant
 * public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override//该方法为继承接口BeanFactoryPostProcessor的方法，用于初始化容器
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("当前容器中有:"+beanFactory.getBeanDefinitionCount()+"个bean");
	}
	@Override//该方法为新方法，用于动态的注册bean
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		for(int i=0;i<10;i++) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(testBean.class);
			builder.addPropertyValue("name", "tester"+i);
			builder.addPropertyValue("id", String.valueOf(i));
			registry.registerBeanDefinition("testBean"+i,builder.getBeanDefinition() );
		}
	}

}
