package com.river.core.jdk8.MultiThreads;

import java.util.concurrent.ForkJoinPool;

import com.river.core.jdk8.MultiThreads.AccumulatorRecursiveAction.AccumulatorHelper;

/**
 * ForkJoint
 * 
 * @author riverplant
 *
 */
public class ForkJoinPoolTest {
	private static int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static void main(String[] args) {
		AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
		ForkJoinPool forkJointPool = new ForkJoinPool();
		Integer result = forkJointPool.invoke(task);
//		AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
//		forkJointPool.invoke(action);//调用compute()->AccumulatorHelper.accumulate(result);
//		Integer result2 = AccumulatorRecursiveAction.AccumulatorHelper.gerResult();//输出返回值
//		AccumulatorRecursiveAction.AccumulatorHelper.reset();//清空AtomicInteger
		System.out.println(result);
	}
	/**
	 * 
	 * @return
	 */
	private static int calc() {
		int result = 0;
		 
		for(int i=0;i<data.length;i++) {
			result+=data[i];
		}
		return result;
	}
	
	

}
