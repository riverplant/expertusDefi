package com.example.java8.filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.java8.dto.Apple;

public class FilterApple {
	/**
	 * 
	 * @author riverplant
	 *当接口只有一个方法，可以注解为functionalInterface
	 *可以有静态方法和default方法
	 *例如只有一个run方法的Runnable 接口
	 */
@FunctionalInterface
public static interface AppleFilter{
	boolean filter(Apple apple);
}
/**
 * 
 * @author riverplant
 *
 */
public static class GreenAnd150WeightFilter implements AppleFilter{
	@Override
	public boolean filter(Apple apple) {
		return (apple.getColor().equalsIgnoreCase("green"))&&(apple.getWeight()>=160);
	}	
}

/**
 * 
 * @param apples
 * @param appleFilter:function interface
 * @return
 */
public static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
	List<Apple> list = new ArrayList<Apple>();
	for(Apple apple:apples) {
		if(appleFilter.filter(apple)) {
			list.add(apple);
		}
	}
	return list;
}


public static void main(String[] args) throws InterruptedException {
	List<Apple>list = new ArrayList<Apple>();//初始化

	List<Apple> lambdaResult = 
			findApple(list,apple-> apple.getColor().equalsIgnoreCase("green"));
	
	System.out.println(lambdaResult);
	
	new Thread(()->System.out.println(Thread.currentThread().getName())).start();
	Thread.currentThread().join();
}//main

public static void ComparatorDemo() {
	Comparator<Apple> byColor = new Comparator<Apple>() {
		@Override
		public int compare(Apple o1, Apple o2) {
			return o1.getColor().compareTo(o2.getColor());
		}		
	};	
	Comparator<Apple> byColor2 = (o1,o2) -> o1.getColor().compareTo(o2.getColor());//lambda表达式
	Comparator<Apple> byColor3 = (o1,o2) ->{ return o1.getColor().compareTo(o2.getColor());};//加{}的写法
	List<Apple> appels = Collections.emptyList();
	appels.sort(byColor);//使用Comparator为list排序!!!!!!
}//ComparatorDemo
}
