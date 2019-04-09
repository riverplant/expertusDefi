package com.example.java8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import com.example.java8.dto.Apple;

/**
 * 使用自己创建的Collector
 * 
 * @author riverplant
 *
 */
public class CustomerColletorAction {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100),
			new Apple("green", 80), new Apple("red", 125), new Apple("yellow", 95));

	public static void main(String[] args) {
		Collector<String, List<String>, List<String>> myCollector = new MyListCollector<>();
		List<String>result = source.stream()
		        .filter(i->i.getWeight()>100)
		        .map(Apple::getColor)
		        .collect(myCollector);
		
		List<String>result2 = source.parallelStream()
		        .filter(i->i.getWeight()>100)
		        .map(Apple::getColor)
		        .collect(myCollector);
		
		System.out.println(result);
	}
}
