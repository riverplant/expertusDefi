package com.river.core.jdk8.MultiThreads;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RecursiveAction:无返回值 public abstract class RecursiveAction extends
 * ForkJoinTask<Void>
 * 
 * @author riverplant
 *
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private final int start;
	private final int end;
	private final int[] data;
	private final int limit = 3;

	public AccumulatorRecursiveAction(int start, int end, int[] data) {
		super();
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override // 无返回值
	protected void compute() {
		if ((end - start) < limit) {
			int result = 0;
			// 可以直接算
			for (int i = start; i < end; i++) {
				result += data[i];
			}
			AccumulatorHelper.accumulate(result);
			// TODO
		} else {
			// 进入递归
			int mid = (end + start) / 2;
			AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
			AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
			left.fork();
			right.fork();
			left.join();
			right.join();
		}
	}
	/**
	 * 
	 * @author riverplant
	 *
	 */
	static class AccumulatorHelper{
		private static final AtomicInteger result = new AtomicInteger(0);
		static void accumulate(int value) {
			 result.getAndAdd(value);
		}
		
		 static int gerResult() {
			return result.get();
		}
		static void reset() {
			result.set(0);
		}
	}
}
