package com.example.demo.oop.compagne.desginpattern.task.decorator;

public class TransactionalRunnable implements Runnable {
    private final Runnable innerRunnable;
    
	public TransactionalRunnable(Runnable innerRunnable) {
		this.innerRunnable = innerRunnable;
	}
	@Override
	public void run() {
		boolean shouldRollback = false;
		try {
			long startTime = System.currentTimeMillis();
			System.out.println("Transaction started at "+ startTime);
			beginTransaction();
			innerRunnable.run();
			System.out.println("Transaction finished,Elapsed time: "+ (System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			shouldRollback = true;
			throw e;
		}finally {
			if(shouldRollback) {
				rollback();
			}else {
				commit();
			}
		}
		
	}

	private void commit() {
		System.out.println("commit");
	}

	private void rollback() {
		System.out.println("rollback");	
	}

	private void beginTransaction() {
		System.out.println("beginTransaction");	
	}

}
