package com.example.java8.future.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * API 线程为守护线程
 * 
 * @author riverplant
 *
 */
public class CompletableFutureInActionDemo2 {

	private final static Random RANDOM = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);
			return t;
		});
		/**
		 * 
		 */
		CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return 1;
		}).runAfterBoth(CompletableFuture.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName());
					return 1;}), ()->System.out.println("done...."));
		/**
		 * 其中的一个完成就输出
		 */
		CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 1;})
		                 .applyToEither(CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 2;}), 
		                		 v->v*10).thenAccept(System.out::println);
		
		CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 1;})
        .acceptEither(CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 2;}), 
        		System.out::println);
		
		CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 1;})
        .runAfterEither(CompletableFuture.supplyAsync(()->{System.out.println("T am future 1");return 2;}), 
        		() -> System.out.println("done.."));//用于日志记录
		Thread.sleep(1000L);
	}

}