package com.example.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.java8.dto.Dish;

/**
 * Stream可以并行处理
 * @author riverplant
 *
 */
public class SimpleStream {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 300, Dish.Type.MEAT));
    /**
     * 
     * @param menu
     * @return
     */
	private static List<String> getDisNameByStream(List<Dish> menu) {
		return menu.stream().filter(i -> i.getCalories() < 400)
		.sorted(Comparator.comparing(Dish::getCalories))
		.map(i -> i.getName())
		.collect(Collectors.toList());
	}

	public static void main(String[] args) {

	}
}
