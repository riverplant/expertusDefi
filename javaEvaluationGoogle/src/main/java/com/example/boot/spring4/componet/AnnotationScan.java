package com.example.boot.spring4.componet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import com.example.boot.spring4.config.JeepConfig;
/**
 * 
 * @author riverplant
 *该类用于定义扫描com.example.boot.spring4下所有的组件，
 * excludeFilters用于定义扫描中排除的有注解的类(@configuration,@controller,...)
 */
@ComponentScan(basePackages="com.example.boot.spring4",excludeFilters=@Filter(type=FilterType.ASSIGNABLE_TYPE,classes=JeepConfig.class))//通过该配置来指定扫描包
@Configuration
public class AnnotationScan {

}
