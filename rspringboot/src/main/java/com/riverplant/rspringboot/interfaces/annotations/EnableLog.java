package com.riverplant.rspringboot.interfaces.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

import org.springframework.context.annotation.Import;

import com.riverplant.rspringboot.impl.MyImportBeanDefinitionRegistrar;
import com.riverplant.rspringboot.impl.MyImportSelector;

/**
 * 自定义注解
 * @author riverplant
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import({MyImportBeanDefinitionRegistrar.class})
public @interface EnableLog {
//String name();
String[] packages();//只要bean的包在packages里，就打印出该对象
}
