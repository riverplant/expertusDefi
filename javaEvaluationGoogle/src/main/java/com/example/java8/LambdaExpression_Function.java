package com.example.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import com.example.java8.dto.Apple;

/**
 * lambda表达式_Function: R apply(T t);
 * 
 * @author riverplant
 *
 */
public class LambdaExpression_Function {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100));

	/**
	 * 
	 * @param Apples
	 * @return
	 */
	private static List<Apple> testFunction(List<Apple> Apples, Function<Apple, Apple> function) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : Apples) {
			result.add(function.apply(apple));
		}
		return result;
	}

	/**
	 * IntFunction<Double>:输入固定为一个int类型，返回一个Double类型
	 * 
	 * @param i
	 * @param function
	 * @return
	 */
	private static double testIntFunction(int i, IntFunction<Double> function) {

		return function.apply(i);
	}

	/**
	 * public interface BiFunction<T, U, R>
	 * @param color
	 * @param weight
	 * @param Bifunction
	 * @return
	 */
	private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> Bifunction) {

		return Bifunction.apply(color, weight);
	}

	public static void main(String[] args) {
		List<Apple> result = testFunction(source, a -> {
			a.setWeight(a.getWeight() * 10);
			return a;
		});
		System.out.println(result);
		// i*100.0d
		double result2 = testIntFunction(10, i -> Double.parseDouble(String.valueOf(10)));
		System.out.println(result2);

		testBiFunction("green", 100, (color, weight) -> new Apple(color, weight));
	}
}
