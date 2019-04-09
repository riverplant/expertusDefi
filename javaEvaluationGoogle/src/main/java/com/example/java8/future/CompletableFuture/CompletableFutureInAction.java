package com.example.java8.future.CompletableFuture;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * 
 * @author riverplant
 *
 */
public class CompletableFutureInAction {

	private final static Random RANDOM = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		//
		CompletableFuture<Double> completableFuture = new CompletableFuture<Double>();
		new Thread(() -> {
			double value = get();
			/**
			 * 处理完成调用completableFuture
			 */
			completableFuture.complete(value);
		}).start();
        //此时可以执行其它的业务逻辑，不卡顿
		System.out.println("此时执行其它的业务逻辑.....................");
		/**
		 * 执行结束后调用
		 */
		//whenComplete(BiConsumer<? super Double, :结果
		//? super Throwable> action) ：异常
		completableFuture.whenComplete((result,exception)->{ 
			Optional.ofNullable(result).ifPresent(System.out::println);//结果
			Optional.ofNullable(exception).ifPresent(error->error.printStackTrace());//异常
		} );
		try {
			Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 static double get() {
		/**
		 * 假设与数据库交互..........
		 */
		try {
			Thread.sleep(RANDOM.nextInt(10000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RANDOM.nextDouble();
	}
}
