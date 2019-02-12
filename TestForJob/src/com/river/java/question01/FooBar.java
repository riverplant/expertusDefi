package com.river.java.question01;

public class FooBar {

	public static void main(String[] args) {
		 FooBar main = new FooBar();
	        for (int i = 1; i <= 50; i ++) {
	            System.out.println(main.fooBar(i));
	        }
	}
	
	 String fooBar(int value) {
	        // TODO: Insert your code here
		 String result = "";
	    	if(value%3==0) {
	    		result = "Foo";
	    	}else if(value%5==0) {
	    		result = "Bar";
	    	}else {
	    		result = String.valueOf(value);
	    	}
	        return result;
	    }

}
