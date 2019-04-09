package com.example.java8.parallel;

import java.util.concurrent.ForkJoinPool;

import com.example.java8.parallel.outil.AccmulatorRecursiveTask;
import com.example.java8.parallel.outil.AccumulationRecursiveAction;

/**
 * 
 * @author riverplant
 *
 */
public class MyForkJoinPool {

	private static int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static void main(String[] args) {
		AccmulatorRecursiveTask task = new AccmulatorRecursiveTask(0,data.length,data);
		AccumulationRecursiveAction action = new AccumulationRecursiveAction(0,data.length,data);
		ForkJoinPool pool = new ForkJoinPool();
		Integer result = pool.invoke(task);
		
		pool.invoke(action);//执行action无返回值
		System.out.println(AccumulationRecursiveAction.AccumulatorHelper.getResult());
		System.out.println(result);
	}
	
}
