package com.example.java8.parallel.outil;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import com.example.java8.parallel.outil.spliterator.MySpliteratorText;
/**
 * 
 * @author riverplant
 *
 */
public class SpliteratorInAction {
	static String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
			"\n"+"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
			"\n"+"cccccccccccccccccccccccccccccccccccccccccccccccccccc"+
			"\n"+"ddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
			"\n"+"eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
			"\n"+"fffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+
			"\n"+"ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
	public static void main(String[] args) {
       
		MySpliteratorText mySpliteratorText =  new MySpliteratorText(data,"\n");
		
		Optional.ofNullable(mySpliteratorText.stream().count())
		.ifPresent(System.out::println);
		
		//mySpliteratorText.stream().forEach(System.out::println);
		mySpliteratorText.stream().filter(i->!i.equals("")).forEach(System.out::println);
		mySpliteratorText.parallelstream().filter(i->!i.equals("")).forEach(System.out::println);
		/*IntStream intStream = IntStream.rangeClosed(0, 10);
		
		Spliterator.OfInt spliterator = intStream.spliterator();
         
		IntConsumer intConsumer =i->System.out.println(i);
         /**
          * 循环，知道条件为false为止
          *  @Override
        default void forEachRemaining(IntConsumer action) {
            do { } while (tryAdvance(action));
        }
          
		spliterator.forEachRemaining(intConsumer);*/
		
		
		
	}
}
