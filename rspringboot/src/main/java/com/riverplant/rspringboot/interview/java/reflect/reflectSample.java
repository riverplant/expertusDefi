package com.riverplant.rspringboot.interview.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * java反射
 * @author riverplant
 *
 */
public class reflectSample {

	public static void main(String[] args) {
		try {
			Class rc = Class.forName("com.riverplant.rspringboot.interview.java.reflect.robot");
			Field name = rc.getDeclaredField("name");//获得属性
			name.setAccessible(true);
			
			robot rb = (robot) rc.newInstance();
			name.set(rb, "Alice");
			System.out.println("className = "+ rb.getClass().getName());
			Method getHello2 = rb.getClass().getMethod("sayHello", String.class);
			Method getHello = rc.getDeclaredMethod("throwHello", null);
			//getHello2.setAccessible(true);
			getHello.setAccessible(true);
			Object str =  getHello.invoke(rb, null);
			System.out.println(str.toString());
//			 getHello2.invoke(rb, "bob");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
