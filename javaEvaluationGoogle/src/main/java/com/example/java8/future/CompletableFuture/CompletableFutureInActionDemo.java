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
public class CompletableFutureInActionDemo {

	private final static Random RANDOM = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);
			return t;
		});
		CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10))// 第一次异步处理
				.thenApplyAsync(v -> Integer.sum(v, 10))// 第二次异步处理
				.whenComplete((v, t) -> System.out.println(v));

		CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10)).handle((v, t) -> Integer.sum(v, 10))
				.whenComplete((v, t) -> System.out.println(v)).thenRunAsync(System.out::println, executor)
				.thenRun(System.out::println);

		CompletableFuture.supplyAsync(() -> 1).thenAccept(System.out::println);

		CompletableFuture.supplyAsync(() -> 1).thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
				.thenAccept(System.out::println);
		/**
		 * thenCombine(CompletionStage<? extends Double> other,
		 *  BiFunction<? superInteger, ? super Double, ? extends Double> fn)
		 *  CompletionStage<? extends Double> other:另外的一个CompletableFuture2
		 *  BiFunction fn: 对CompletableFuture1和CompletableFuture2的值进行操作
		 */
		CompletableFuture.supplyAsync(() -> 1)
				.thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
				.thenAccept(System.out::println);

		CompletableFuture.supplyAsync(() -> 1)
		.thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), 
				(r1, r2) ->System.out.println(r1 + r2) );
		Thread.sleep(1000L);
	}

}