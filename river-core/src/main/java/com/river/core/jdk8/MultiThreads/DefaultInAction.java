package com.river.core.jdk8.MultiThreads;

/**
 * 
 * @author riverplant
 *
 */
public class DefaultInAction {

	private void confus(Object obj) {
		System.out.println("Object");
	}

	private void confus(int[] i) {
		System.out.println("int[]");
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		A a = () -> 10;
		System.out.println(a.size());
		System.out.println(a.isEmpty());
	}

	/**
	 * 
	 * @author riverplant
	 *
	 */
	private interface A {
		int size();// 该方法用于获取容器大小
		/**
		 * 判断A容器是否为空
		 * 
		 * @return
		 */
		default boolean isEmpty() {
			return size() == 0;
		}
		default void hello() {
			System.out.println("==A.hello==");
		}
	}// A

	private interface B extends A{
		@Override
		default void hello() {
			System.out.println("==B.hello==");
		}
	}//B
	/**
	 * 
	 * @author riverplant
	 *当调用c.hello()输出的是B.hello
	 */
	private static class c implements A,B{

		@Override
		public int size() {
			
			return 0;
		}
		@Override
		 public void hello() {
			 System.out.println("==C.hello==");
		 }
	}
}
