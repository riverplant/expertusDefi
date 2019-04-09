package com.riverplant.rspringboot.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
/**
 * 
 * @author riverplant
 *
 */
public class MyEncodingCondition implements Condition {
	private static final String ENCODINGUTF8 = "utf-8";
	private static final String ENCODINGGBK = "gbk";

	@Override//返回true进行自动配置，返回false不配置
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean flag = false;
		
		String encoding = System.getProperty("file.encoding");
		if(encoding!=null) {
			if( ENCODINGUTF8.equalsIgnoreCase(encoding)) {
				flag = true;
				System.out.println("encoding is utf-8");
			}else if(ENCODINGGBK.equalsIgnoreCase(encoding)) {
				flag = true;
				System.out.println("encoding is gbk");
			}
		}
		return flag;
	}

}
