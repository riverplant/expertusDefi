package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.java8.dto.Dish;
/**
 * int占用内存4byte/32bit
 * 为了节约内存可以使用IntStream
 * @author riverplant
 *
 */
public class NumericStream {
	static List<Dish> menu = Arrays.asList(new Dish("porc", false, 200, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 300, Dish.Type.MEAT));
	static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	public static void main(String[] args) {
		list.stream().filter(i->i.intValue()>3).reduce(Integer::sum).ifPresent(System.out::println);
		IntStream intStream  = list.stream().mapToInt(i->i.intValue());
		Stream<Integer> stream = intStream.boxed();//进行封箱
		intStream.filter(i->i>3).sum();
		
		//list.stream().mapToInt(i->i.intValue()).sum();
		IntStream.rangeClosed(1, 100);//IntStream,内容为1->100
		
	}
	/**
	 * 找出符合沟谷定律的数字
	 * @param i
	 */
	public void searchggdl(int i) {
		IntStream.rangeClosed(1, 100)
		.filter(a->Math.sqrt(a*a+i*i) % 1 == 0)
		.boxed()//封装成对象后使用map方法
		.map(x->new int[]{i,x,(int)Math.sqrt(i*i+x*x)})
		.forEach(r->System.out.println("a="+r[0]+"b="+r[1]+"c="+r[2]));;
		
		IntStream.rangeClosed(1, 100)
		.filter(a->Math.sqrt(a*a+i*i) % 1 == 0)
		.mapToObj(x->new int[]{i,x,(int)Math.sqrt(i*i+x*x)})
		.forEach(r->System.out.println("a="+r[0]+"b="+r[1]+"c="+r[2]));
	}
}
