package com.example.java8;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.example.java8.dto.Apple;
import com.example.java8.dto.ComplexApple;
import com.example.java8.interfaces.ThreeFunction;

/**
 * Lambda方法推导
 * 1.通过静态方法可以使用 Function<String,Integer> f = Integer::parseInt; Integer result = f.apply("123");
 * 2.对象的实例方法:
 * 3.通过构造函数:BiFunction<String,Long,Apple>appleFunction = Apple::new;
 *               appleFunction.apply("red",100);
 * @author riverplant
 *
 */
public class MethodReference {
	static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 100));
    /**
     * Consumer方法推导
     * @param consumer
     * @param t
     */
	private static <T> void useConsumer(Consumer<T> consumer,T t) {
		consumer.accept(t);
		consumer.accept(t);
	}
	
	

	public static void main(String[] args) {
		Consumer<String> consumer = s->System.out.println(s);
		useConsumer(consumer,"hello");
		useConsumer(s->System.out.println(s),"hello");
		/**
		 * 与Consumer的描述相同，都是一个入参，没有返回值
		 *  public final static PrintStream out = null;静态Object
		 *  
		 * accept(T t)
		 *  public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
             }}
		 */
		useConsumer(System.out::println,"hello");
		//@FunctionalInterface
		//public interface Comparator<T> 
		//int compare(T o1, T o2);
		source.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
		
		BiFunction<String,Integer,Character> f2 = String::charAt;
		Character c = f2.apply("hello", 3);
		
		Supplier<String> supplier = String::new;
		System.out.println(supplier.get());
		//使用自定义的FunctionalInterface
		ThreeFunction<String,Long, String, ComplexApple> threeFunction = ComplexApple::new;
		ComplexApple capple = threeFunction.apply("Green",123L,"富士苹果");
		System.out.println(capple);
		
		//Comparator方法推导
		source.sort(Comparator.comparing(Apple::getColor));
		
	}
}
