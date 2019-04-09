package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.List;

import com.example.java8.dto.Dish;
import com.google.common.base.Optional;

/**
 * Reduce:聚合terminal
 * reduce(Integer identity, BinaryOperator<Integer> accumulator)
 * @author riverplant
 *
 */
public class StreamReduce {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 300, Dish.Type.MEAT));
	static List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	public static void main(String[] args) {
		
		//list.stream().reduce(Integer::sum).ifPresent(System.out::println);
		
		//Integer result = list.stream().reduce(0,(i,j)->i+j);
		//用reduce求list最大最小值!!!!!!!!!!!!!!!!!!!!!!!
		//list.stream().reduce((i,j)->i>j?i:j).ifPresent(System.out::println);
		list.stream().reduce(Integer::max).ifPresent(System.out::println);
		list.stream().reduce(Integer::min).ifPresent(System.out::println);
		list.stream().reduce(Integer::sum).ifPresent(System.out::println);
		Optional.of(list.stream().reduce(1,(i,j)->i*j)).get();
	}
}
