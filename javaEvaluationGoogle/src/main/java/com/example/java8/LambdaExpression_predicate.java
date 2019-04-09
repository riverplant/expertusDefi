package com.example.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import com.example.java8.dto.Apple;

/**
 * lambda表达式_predicate
 * 
 * @author riverplant
 *
 */
public class LambdaExpression_predicate {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100));

	/**
	 * 
	 * @param source
	 * @param predicate
	 * @return
	 */
	private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : source) {
			if (predicate.test(apple))
				result.add(apple);
		}
		return result;
		// return (List<Apple>) source.stream().filter(predicate);
	}

	/**
	 * Longpredicate:相当于predicata<long>必须比较long类型
	 * 
	 * @param source
	 * @param predicate
	 * @return
	 */
	private static List<Apple> Longpredicate_filter(List<Apple> source, LongPredicate predicate) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : source) {
			if (predicate.test(apple.getWeight()))
				result.add(apple);
		}
		return result;
	}
	
	private static List<Apple> Bipredicate_filter(List<Apple> source, BiPredicate<Long,String> predicate) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : source) {
			if (predicate.test(apple.getWeight(),apple.getColor()))
				result.add(apple);
		}
		return result;
	}

	public static void main(String[] args) {
		Comparator<Object> byColor = (a1, a2) -> {
			return ((Apple) a1).getColor().compareTo(((Apple) a2).getColor());
		};
		Collections.emptyList().sort(byColor);
		// R get()
		Supplier<Apple> supplier = () -> new Apple();

		List<Apple> greenList = filter(source, apple -> apple.getColor().equalsIgnoreCase("green"));
		List<Apple> longList = Longpredicate_filter(source, w -> w>100);
		List<Apple> biList = Bipredicate_filter(source, (w,c)->(w>100 && c.equals("green")));
		/************************************************************/
		/*
		 * Runnable r1 = ()->System.out.println("hello"); Runnable r2 = new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * System.out.println("hello2"); }
		 * 
		 * };//r2
		 * 
		 * 
		 * process(r1); process(r2); process(()->System.out.println("hello3"));
		 */
	}// main

	private static void process(Runnable r) {
		r.run();
	}

	/**
	 * 自定义一个functioninterface
	 * 
	 * @author riverplant
	 *
	 */
	@FunctionalInterface
	public interface Adder {
		int add(int a, int b);
	}

	/**
	 * 继承FunctionalInterface,该接口有一个继承的int add(int a,int b)，还有一个自己的int add(long
	 * a,long b) 所以不是FunctionalInterface
	 * 
	 * @author riverplant
	 *
	 */
	public interface smartAdder extends Adder {

		int add(long a, long b);
	}

}
