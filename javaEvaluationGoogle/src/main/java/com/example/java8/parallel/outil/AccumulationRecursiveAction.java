package com.example.java8.parallel.outil;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无返回值
 * 
 * @author riverplant
 *
 */
public class AccumulationRecursiveAction extends RecursiveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int start;

	private final int end;

	private final int[] data;

	private final int LIMIT = 3;

	public AccumulationRecursiveAction(int start, int end, int[] data) {
		super();
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected void compute() {
      if((end - start)<=LIMIT){
    	  for(int i=start;i<end;i++) {
    		  AccumulatorHelper.accumulate(data[i]);
    	  }
      }else {
		  int mid =  (end + start)/2;
	    	 AccumulationRecursiveAction left = new AccumulationRecursiveAction(start, mid, data);
	    	 AccumulationRecursiveAction right = new AccumulationRecursiveAction(mid, end, data);
	    	 left.fork();
	    	 right.fork(); 
	    	 left.join();
	    	 right.join();
	  }
	}//compute

	public static class AccumulatorHelper{
		private static final AtomicInteger result = new AtomicInteger(0);
		static void accumulate(int value) {
			result.getAndAdd(value);
		}
		public static int getResult() {
			return result.get();
		}
		
		static void reset(int value) {
			result.set(0);
		}
	}
}//
