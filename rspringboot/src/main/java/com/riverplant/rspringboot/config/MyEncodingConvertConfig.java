package com.riverplant.rspringboot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import com.riverplant.rspringboot.impl.GbkEncodingConvert2;
import com.riverplant.rspringboot.impl.MyEncodingCondition;
import com.riverplant.rspringboot.impl.Utf8EncodingConvert;
import com.riverplant.rspringboot.interfaces.MyEncodingConvert;

/**
 * 
 * @author riverplant
 *
 */
@SpringBootConfiguration
@Conditional(MyEncodingCondition.class)//可以注解在类上或者方法上，可以注解多个condition,只有都返回true才会装配
public class MyEncodingConvertConfig {
    @Bean
    @Conditional(MyEncodingCondition.class)
	public MyEncodingConvert createUtf8EncodingConvert() {
		 return new Utf8EncodingConvert();
	}
    
    @Bean
    @Conditional(MyEncodingCondition.class)
   	public MyEncodingConvert createGBKEncodingConvert() {
   		 return new GbkEncodingConvert2();
   	}
}
