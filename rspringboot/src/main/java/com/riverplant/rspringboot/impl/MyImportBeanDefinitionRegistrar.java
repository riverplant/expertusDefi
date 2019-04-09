package com.riverplant.rspringboot.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import com.riverplant.rspringboot.interfaces.annotations.EnableLog;
/**
 * 
 * @author riverplant
 * 将MyBeanPostProcessor装配入spring容器
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override//通过BeanDefinitionRegistry来动态注入bean
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> map =   importingClassMetadata.getAnnotationAttributes(EnableLog.class.getName());
		String[] packArr = (String[]) map.get("packages");
		List<String> packages = Arrays.asList(packArr);
		System.out.println("packages="+packages);
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(MyBeanPostProcessor.class);
		bdb.addPropertyValue("packages", packages);//通过该方法给MyBeanPostProcessor中的packages属性初始化
		BeanDefinition beanDefinition = bdb.getBeanDefinition();
		registry.registerBeanDefinition("myBeanPostProcessor", beanDefinition);
	}

}
