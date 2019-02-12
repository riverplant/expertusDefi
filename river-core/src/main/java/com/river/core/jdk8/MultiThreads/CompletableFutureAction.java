package com.river.core.jdk8.MultiThreads;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture
 * 
 * @author riverplant
 *
 */
public class CompletableFutureAction {
	private final static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Double> completableFuture = new CompletableFuture<Double>();
		new Thread(() -> {
			double value = get();// 执行该方法时会卡顿
			completableFuture.complete(value);// 将该方法放入CompletableFuture中
		}).start();
		System.out.println("======no======block.....");// 异步执行打印，不需要等待
		/**
		 * Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
		 * CompletableFuture<Double>
		 * java.util.concurrent.CompletableFuture.whenComplete(BiConsumer<? super
		 * Double, ? super Throwable> action) v:结果completableFuture.get() t:异常
		 * completableFuture.whenComplete回调方式
		 */
		completableFuture.whenComplete((v, t) -> {
			Optional.ofNullable(v).ifPresent(System.out::println);// 输出结果
			Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());// 打出异常
		});
	}

	/**
	 * 
	 * @return
	 */
	 static double get() {
		try {
			Thread.sleep(random.nextInt(10000));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return random.nextDouble();
	}

	/**
	 * 通过静态方法supplyAsync
	 * 
	 * @throws InterruptedException
	 */
	private static void supplyAsync() throws InterruptedException {
		AtomicBoolean flag = new AtomicBoolean(false);
		ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);
			t.setDaemon(false);// 设置为非守护线程，不会随着主程序结束而实现自动退出
			return t;
		});
		/**
		 * <Double> CompletableFuture<Double> java.util.concurrent.CompletableFuture.
		 * supplyAsync(Supplier<Double> supplier, Executor executor) Executor
		 * executor：用于异步执行的异步程序 supplyAsync:异步提交，whenComplete完成后消费得到的结果
		 */
		CompletableFuture.supplyAsync(CompletableFutureAction::get, executor).whenComplete((v, t) -> {// 该线程为守护线程，所以会在主线程结束的时候直接结束
			Optional.ofNullable(v).ifPresent(System.out::println);
			flag.set(true);
			Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
		});
		System.out.println("并行中的任务.......");
		executor.shutdown();// 结束异步程序
		// while (!flag.get()) {
		// Thread.sleep(1);
		// }
		/**
		 * ExecutorService java.util.concurrent.Executors.newFixedThreadPool(int
		 * nThreads, ThreadFactory threadFactory)
		 */
		// ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
		// Thread t = new Thread(r);
		// t.setDaemon(true);//设置为守护线程，会实现自动退出
		// return t;
		// });
		// executor.execute(() -> System.out.println("test..."));// 执行完后不会退出线程
		// executor.shutdown();//执行该方法让它退出
	}// supplyAsync

	/**
	 * ExecutorService java.util.concurrent.Executors.newFixedThreadPool(int
	 * nThreads, ThreadFactory threadFactory)
	 * 
	 * ThreadFactory:Thread newThread(Runnable r);
	 */
	private static void CompletableFuturedemo() {
		ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);// r:Runnable
			t.setDaemon(false);
			return t;
		});

		CompletableFuture.supplyAsync(CompletableFutureAction::get, executor)
				.thenApply(CompletableFutureAction::multiply)// 结果加工
				.whenCompleteAsync((v, t) -> {
					Optional.ofNullable(v).ifPresent(System.out::println);
				});
	}// demo

	private static double multiply(double value) {

		try {
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value * 10;

	}

	/**
	 * 
	 * @param executor
	 */
	private static void CompletableFuturedemo2(ExecutorService executor) {
		List<Integer> productionIds = Arrays.asList(1, 2, 3, 4, 5);
		List<Double> list = productionIds.stream()
		.map(i->CompletableFuture.supplyAsync(()->queryProduction(i), executor))//多线程
		.map(future-> future.thenApply(CompletableFutureAction::multiply))//加工结果
		.map(CompletableFuture::join).collect(Collectors.toList());//合并
		                      
		                 
		// Stream<Object> stream = productionIds.stream()
		// .map(i->CompletableFuture.supplyAsync(()->queryProduction(i),executor )
		// .thenApply(CompletableFutureAction::multiply));
		/**
		 * 
		 * <CompletableFuture<Double>> Stream<CompletableFuture<Double>>
		 * java.util.stream.Stream.map(Function<? super Integer, ? extends
		 * CompletableFuture<Double>> mapper)
		 */
		Stream<CompletableFuture<Double>> completableFuture = productionIds.stream()
				//i -> CompletableFuture.supplyAsync:用多线程进行处理
				//Function<? super Integer,
				.map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor));
		Stream<CompletableFuture<Double>> multiplayFuture = completableFuture
				//对结果进行加工
				.map(future -> future.thenApply(CompletableFutureAction::multiply));
		                    //对多线程处理的结果进行合并
		List<Double> result = multiplayFuture.map(CompletableFuture::join).collect(Collectors.toList());
	}

	/**
	 * 通过id查找产品
	 * 
	 * @param i
	 * @return
	 */
	private static double queryProduction(int i) {
		return get();
	}
	
}
