package com.example.java8.future.CompletableFuture;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * @author riverplant
 *
 */
public class CompletableFutureInAction2 {

	private final static Random RANDOM = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		AtomicBoolean finished = new AtomicBoolean(false);
		ExecutorService executor = Executors.newFixedThreadPool(2,r->{
			Thread t = new Thread(r);
			t.setDaemon(false);
			return t;
		});
		/**
		 * 本身为守护线程，通过executor将线程设置为非守护
		 */
		CompletableFuture.supplyAsync(CompletableFutureInAction::get,executor)
		                 .whenComplete((r,e)->{
		                	Optional.ofNullable(r).ifPresent(System.out::println); 
		                	finished.set(true);
		                 });
		while(finished.get()==false) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//		try {
//			Thread.currentThread().join();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
/**************************************************************************************/
		/**
		 * 将executor中的线程设置为守护线程
		 * newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
		 */
	/*	ExecutorService executor =  Executors.newFixedThreadPool(2,r->{
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		});
		
		executor.execute(()->{
			System.out.println("test....");
		} );
		
		executor.shutdown();*///如果非守护线程必须通过shutdown来结束线程
/**************************************************************************************/
		//通过静态方法来创建
		CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(CompletableFuture::new);

		new Thread(()->{
			double value = get();
			completableFuture.complete(value);
		} ).start();
		
		System.out.println("此时执行其它的业务逻辑.....................");
		completableFuture.whenComplete((result,exception)->{
			Optional.ofNullable(result).ifPresent(System.out::println);
			Optional.ofNullable(exception).ifPresent(t -> t.printStackTrace());
		});
		try {
			Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static double get() {
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
