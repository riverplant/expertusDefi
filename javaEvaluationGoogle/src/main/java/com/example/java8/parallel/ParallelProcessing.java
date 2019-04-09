package com.example.java8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 
 * @author riverplant
 *
 */
public class ParallelProcessing {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());//获得当前配置核数
		System.out.println("The best process time=>"+measureSumPerformance(ParallelProcessing::itreatorStream,1000000));
		System.out.println("The best process2 time=>"+measureSumPerformance(ParallelProcessing::parallelStream,1000000));
	}
	
	/**
	 * 获取1到limit的加法器
	 * @param limit
	 * @return
	 */
	private static long itreatorStream(long limit) {
		return Stream.iterate(1L, i->i+1)
		       .limit(limit)
		       .reduce(0L,Long::sum);
	}
	/**
	 * 最优方法
	 * @param limit
	 * @return
	 */
	private static long parallelStream(long limit) {
		return LongStream.range(1, limit).parallel().sum();
	}
	
	private static long measureSumPerformance(Function<Long,Long> adder, long limit) {
		long fastest = Long.MAX_VALUE;
		for(int i=0;i<10; i++) {
			long startTimestamp = System.currentTimeMillis();	
			long result = adder.apply(limit);	
			long durationTimestamp = System.currentTimeMillis()-startTimestamp;	
			System.out.println("The result of sum = "+ result);
			fastest = durationTimestamp<fastest?durationTimestamp:fastest;
		}
		return fastest;
	}
	
}
