package com.example.demo.oop.compagne.desginpattern.task;
public abstract class TransctionalRunnable implements Runnable{
	
	protected abstract void doRun();	
    @Override
	public void run() {
    	boolean shouldRollback = false;
		try {
			beginTransaction();
			doRun();
		} catch (Exception e) {
			shouldRollback = true;
			throw new RuntimeException();
		}finally {
			if(shouldRollback) {
				rollback();
			}else {
				commit();
			}
		}
	}
	private void commit() {
		// TODO Auto-generated method stub
		System.out.println("commit");
	}
	private void rollback() {
		
		System.out.println("rollback");
	}
	private void beginTransaction() {
		System.out.println("beginTransaction");
		
	}
}
