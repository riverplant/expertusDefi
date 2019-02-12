package com.river.core.jdk8.MultiThreads;

import java.util.concurrent.RecursiveTask;

/**
 * @author riverplant 
 * RecursiveTask:递归任务
 * Accumulator:累加器
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private final int start;
	private final int end;
	private final int[] data;
	private final int limit = 3;

	public AccumulatorRecursiveTask(int start, int end, int[] data) {
		super();
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override//分成limit数量为一组
	protected Integer compute() {
		// 1.判断数组的下标(end-start)<limit
		if ((end - start) < limit) {
			int result = 0;
			for (int i = start; i < end; i++) {
				result += data[i];
			}
			return result;
		}
		int mid = (end + start) / 2;// 分而治之,将数组分开
		//递归直到(end-start)<limit进入if开始累加并且返回
		AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
		AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
		left.fork();
		right.fork();
		//Integer rightResult = right.compute();
		//Integer leftResult = left.join();
		return left.join() + right.join();
	}

}
