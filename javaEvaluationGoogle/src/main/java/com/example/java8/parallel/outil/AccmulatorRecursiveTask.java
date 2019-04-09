package com.example.java8.parallel.outil;

import java.util.concurrent.RecursiveTask;

/**
 * 累加器
 * public abstract class RecursiveTask<V> extends ForkJoinTask<V> 
 * @author riverplant
 *
 */
public class AccmulatorRecursiveTask extends RecursiveTask<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int start;
	
	private final int end;
	
	private final int[] data;
	
	private final int LIMIT = 3;
	
	public AccmulatorRecursiveTask(int start, int end, int[] data) {
		super();
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected Integer compute() {
		if(end-start<=LIMIT) {
			int result = 0;
			for(int i=start;i<end;i++) {
				result += data[i];
			}
			return result;
		}
		int mid = (start+end)/2;
		AccmulatorRecursiveTask left = new AccmulatorRecursiveTask(start, mid, data);
		AccmulatorRecursiveTask right = new AccmulatorRecursiveTask(mid, end, data);
		left.fork();
		right.fork();
		return left.join() + right.join();
	}

}
