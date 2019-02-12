package com.river.core.jdk8.MultiThreads;

import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterator.OfInt;
import java.util.Spliterator.OfPrimitive;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * spliterator
 * 
 * @author riverplant
 *
 */
public class SpliteratorIntAction {
	public static void main(String[] args) {
		MySpliteratorText text = new MySpliteratorText("sssssssssssssssssssss;sssssssss;aaaaaaaaaa", ";");
		Optional.ofNullable(text.parallelstream().count())
		         .ifPresent(System.out::println);
		text.parallelstream().filter(i->!i.equals("")).forEach(System.out::println);	
	}
	/**public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt>  
	 * 
	 * public interface OfPrimitive<T, T_CONS, T_SPLITR extends
	 * Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends Spliterator<T>
	 */
	private static void Spliterator() {
		IntStream intStream = IntStream.rangeClosed(0, 10);
		Spliterator.OfInt spliterator = intStream.spliterator();
		Consumer<Integer>consumer = i -> System.out.println(i);
		spliterator.forEachRemaining(consumer);
	}

}
