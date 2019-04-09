package com.example.java8.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.java8.dto.Apple;

/**
 * 
 * @author riverplant
 *
 */
public class CollectorIntroduce {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), 
			                                  new Apple("red", 150), 
			                                  new Apple("yellow", 100),
			                                  new Apple("green", 80),
			                                  new Apple("red", 125),
			                                  new Apple("yellow", 95));

	public static void main(String[] args) {
     /**
      * 1.对Apple按照颜色进行分组
      */
		Optional.ofNullable(groupByNormal(source)).ifPresent(System.out::println);
		
	}
	/**
	 * 按照颜色分组
	 * @param apples
	 * @return
	 */
	private static Map<String,List<Apple>> groupByNormal(List<Apple> apples){
		return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
	}
	/**
	 * 
	 * @param apples
	 * @return
	 */
	private static Map<String,List<Apple>> groupByNormal2(List<Apple> apples){
		Map<String,List<Apple>> map =new HashMap<>();//该map是final
		 apples.stream().forEach(appel->{
			 List<Apple> colorList =	 Optional.ofNullable(map.get(appel.getColor())).orElseGet(()->{
				 List<Apple> list = new ArrayList<>();
				 map.put(appel.getColor(), list);
				 return list;
			 });
			 colorList.add(appel); 
		 });
		 return map;
	}
}
