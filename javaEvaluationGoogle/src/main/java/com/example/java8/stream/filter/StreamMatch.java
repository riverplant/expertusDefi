package com.example.java8.stream.filter;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import com.example.java8.dto.Dish;

/**
 * Match
 * //[nonematch|anymatch|allmatch]
 * @author riverplant
 *
 */
public class StreamMatch {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 300, Dish.Type.MEAT));
	static List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	public static void main(String[] args) {
		
		//Stream.allMatch(Predicate<? super Integer> predicate)
		boolean matched = list.stream().allMatch(i->i>0);
		//没有一个元素小于0
		list.stream().noneMatch(i->i<0);
		
		
	}
}
