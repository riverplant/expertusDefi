package com.example.java8.defaults;
/**
 * 
 * @author riverplant
 *
 */
public class DefaultInAction {
	public static void main(String[] args) {
		A a = ()->10;
		a.isVide();
		a.size();
		
	}
    @FunctionalInterface
	private interface A{
    	
		int size();
		
		/**
		 * 
		 * @return
		 */
		default boolean isVide() {
			return size()==0;
		}
	}//A
	
	
	
	
	
}
