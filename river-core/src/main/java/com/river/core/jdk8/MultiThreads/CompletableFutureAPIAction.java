package com.river.core.jdk8.MultiThreads;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * CompletableFuture
 * 
 * @author riverplant
 *
 */
public class CompletableFutureAPIAction {
	private final static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10))// 1+10
				.whenCompleteAsync((v, t) -> { // 异步调用方法
					Optional.ofNullable(v).ifPresent(System.out::println);
				}).whenComplete((v, t) -> {
					Optional.ofNullable(v).ifPresent(System.out::println);
				});
		Thread.sleep(1000L);

		CompletableFuture.supplyAsync(() -> 1).handle((v, t) -> Integer.sum(v, 10))
				.whenComplete((v, t) -> System.out.println(v))
				// .thenRunAsync(System.out::println);
				.thenRun(System.out::println);

		CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10)).thenAccept(System.out::println);// 不对结果进行操作

		CompletableFuture.supplyAsync(() -> 1).thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))// i=1
				.thenAccept(System.out::println);

		CompletableFuture.supplyAsync(() -> 1)
				.thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)// 1+2.0
				.thenAccept(System.out::println);

		CompletableFuture.supplyAsync(() -> 1).thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> {
			System.out.println(r1 + r2);
		});

		// runAfterBoth:两个CompletableFuture 都结束后执行的动作
		CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "is runnint");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}).runAfterBoth(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "is runnint");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 2;
		}), () -> {
			System.out.println("done");
		});

		//applyToEither:当其中的一个执行完就传递给function interface
		CompletableFuture.supplyAsync(()->{
			System.out.println("future 1");
			return CompletableFutureAction.get();
		}).applyToEither(CompletableFuture.supplyAsync(()->{
			System.out.println("future 2");
			return CompletableFutureAction.get();
		}),v->v*10)
		.thenAccept(System.out::println);
		Thread.currentThread().join();
		
		CompletableFuture.supplyAsync(()->{
			System.out.println("future 1");
			return CompletableFutureAction.get();
		}).acceptEither(CompletableFuture.supplyAsync(()->{
			System.out.println("future 2");
			return CompletableFutureAction.get();
		}),i->System.out.println(i));
		
		CompletableFuture.supplyAsync(()->{
			System.out.println("future 1");
			return CompletableFutureAction.get();
		}).runAfterEither(CompletableFuture.supplyAsync(()->{
			System.out.println("future 2");
			return CompletableFutureAction.get();
		}),()->System.out.println("done..."));
		
		//allOf(静态方法)
		List<CompletableFuture<Double>> collect = Arrays.asList(1,2,3,4)
				              .stream()
		                      .map(i->CompletableFuture.supplyAsync(CompletableFutureAction::get))
		                      .collect(Collectors.toList());//获得一个CompletableFuture的list
		//CompletableFuture.allOf需要一个数组
		CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
		.thenRun(() ->System.out.println("done..."));
		
		//anyOf(静态方法)
				List<CompletableFuture<Double>> collect2 = Arrays.asList(1,2,3,4)
						              .stream()
				                      .map(i->CompletableFuture.supplyAsync(CompletableFutureAction::get))
				                      .collect(Collectors.toList());//获得一个CompletableFuture的list
				//CompletableFuture.allOf需要一个数组
				CompletableFuture.anyOf(collect2.toArray(new CompletableFuture[collect2.size()]))
				.thenRun(() ->System.out.println("done..."));
		
		
	}

}
