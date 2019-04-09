package com.riverplant.rspringboot.impl;

import java.util.Set;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import com.riverplant.rspringboot.beans.User;
import com.riverplant.rspringboot.config.MyConfig;
import com.riverplant.rspringboot.interfaces.annotations.EnableLog;
/**
 * 
 * @author riverplant
 * 该类中selectImports方法的返回值为一个动态指定的类数组，该数组中的类被String容器托管
 */
public class MyImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//获得引用了该类的注解的属性
		Set<MethodMetadata> methodMetadatas = importingClassMetadata.getAnnotatedMethods(EnableLog.class.getName());
		//可以根据获得的注解详细信息来动态的返回需要被Spring容器管理的bean
		//动态导入User和MyConfig
		return new String[] {User.class.getName(),MyConfig.class.getName()};
	}

}
