package com.example.java8;

import java.util.ArrayList;
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
 * lambda表达式_Consumer: accepter(T t);
 * 
 * @author riverplant
 *
 */
public class LambdaExpression_Consumer {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100));

	/**
	 * 
	 * @param source
	 * @param consumer
	 */
	private static void simpleTestCounsumer(List<Apple> source,Consumer<Apple>consumer  ) {
		for(Apple a:source) {
			consumer.accept(a);
		}
	}
	private static void simpleBiCounsumer(String c,List<Apple> source,BiConsumer<Apple,String>consumer  ) {
		for(Apple a:source) {
			consumer.accept(a,c);
		}
	}
	public static void main(String[] args) {
		simpleTestCounsumer(source,a->System.out.println(a));
		simpleBiCounsumer("**",source,(a,c)->System.out.println(a.getColor().concat(c)));
	}
}
