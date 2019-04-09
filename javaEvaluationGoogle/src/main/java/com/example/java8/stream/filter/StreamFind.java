package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Find->optional
 * @author riverplant
 *
 */
public class StreamFind {
	static List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	public static void main(String[] args) {
		//findAny:查找任何一个就返回Optional
		//findFirst：查找到第一个就返回 Optional 
		System.out.println(list.stream().filter(i->i%2==0).findAny().orElse(-1));
		list.stream().filter(i->i%2==0).findFirst().orElse(-1);
	}
	/**
	 * 
	 * @param values
	 * @param defaultValue:-1
	 * @param predicate
	 * @return
	 */
	private static int find(Integer[] values,int defaultValue,Predicate<Integer>predicate) {
		for(int i:values) {
			if(predicate.test(i))return i;
		}
		return defaultValue;
	}//find
	
}
