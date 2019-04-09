package com.example.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import com.example.java8.dto.Apple;

/**
 * lambda表达式_Supplier: R get();
 * 在lambda表达式中(匿名函数)使用的变量必须是final类型!!!!!!!!!!!!!!!!!
 * @author riverplant
 *
 */
public class LambdaExpression_Supplier {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100));

	private static Apple createApple(Supplier<Apple> supplier){
		return supplier.get();
	}
	
	public static void main(String[] args) {
      Supplier<String>s = String::new;//函数推导，method inference
      System.out.println(s.get().getClass());
      
      Apple apple = createApple(()->new Apple("green", 120));
      System.out.println(apple);
	}
}
