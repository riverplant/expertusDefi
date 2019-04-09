package com.example.java8.future.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author riverplant
 *
 */
public class CompletableFutureInAction3 {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static List<Integer> Productions = Arrays.asList(1,2,3,4,5);
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);
			t.setDaemon(false);
			return t;
		});
//		CompletableFuture.supplyAsync(() -> get(), service)
//		        .thenApply(CompletableFutureInAction3::multiply)
//				.whenComplete((v, t) -> {
//					Optional.ofNullable(v).ifPresent(System.out::println);
//				});
		
//             Productions.stream().map(i->
//			 CompletableFuture.supplyAsync(() -> query(i), service)
//			 .thenApply(CompletableFutureInAction3::multiply)
//			 .whenComplete((v, t) -> {
//					Optional.ofNullable(v).ifPresent(System.out::println);}));
		          
            Optional.ofNullable(
            		Productions.stream()
            		.map(i->CompletableFuture.supplyAsync(() -> query(i), service))
                    .map(future->future.thenApply(CompletableFutureInAction3::multiply))
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()))
                    .ifPresent(System.out::println);
                                
            List<CompletableFuture<Double>> collect = Arrays.asList(1,2,3,4).stream()
		      .map(i->CompletableFuture.supplyAsync(CompletableFutureInAction3::get))
		      
		      .collect(Collectors.toList());
		      
            CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()] ))
            .thenRun(()->System.out.println("done....")); //所有的CompletableFuture完成后打印   
		 
            CompletableFuture.anyOf(
            	  Arrays.asList(1,2,3,4)
       		     .stream()
       		     .map(i->CompletableFuture.supplyAsync(CompletableFutureInAction3::get))
       		     .collect(Collectors.toList()).toArray(new CompletableFuture[Arrays.asList(1,2,3,4).size()])
            		     ).thenRun(()->System.out.println("done...."));
	}// main

	/**
	 * 
	 * @return
	 */
	private static double get() {
		double result = RANDOM.nextDouble();
		System.out.println(result);
		return result;
	}

	private static double multiply(double value) {
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value * 10d;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	private static double query(int i) {
		return get() * i;
	}

}
