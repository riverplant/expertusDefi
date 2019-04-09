package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.dto.Dish;


/**
 * Filter
 * @author riverplant
 *
 */
public class StreamFilter {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
			                               new Dish("beef", false, 700, Dish.Type.MEAT), 
			                               new Dish("chicken", false, 300, Dish.Type.MEAT));
	public static void main(String[] args) {
    	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    	//获得偶数
    	list.stream().filter(i->i%2==0).distinct().collect(Collectors.toList());
    	//跳过前5个元素
    	list.stream().skip(5).collect(Collectors.toList());
	}
}
