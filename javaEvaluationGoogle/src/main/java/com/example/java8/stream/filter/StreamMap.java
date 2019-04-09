package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.java8.dto.Dish;
/**
 * flatmap:扁平化
 * @author riverplant
 *
 */
public class StreamMap {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT), 
            new Dish("chicken", false, 300, Dish.Type.MEAT));
public static void main(String[] args) {
	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	//每个元素增大两倍
	list.stream().map(i->i*2).collect(Collectors.toList());
	//字符串数组，
	String[] words = {"Hello","World"};
	//{h,e,l,l,o},{W,o,r,l,d}
	//w.split("\\s+")根据空格分隔
	Stream<String[]> stream = Arrays.asList(words).stream().map(w->w.split(""));// Stream<String[]>
	//经过flatMap扁平化处理后，变成h,e,l,l,o,W,o,r,l,d!!!!!!!!
     stream.flatMap(Arrays::stream).distinct().forEach(System.out::print);;
}
}
