package com.example.demo.language.inteview;

public class BoxingDemo {

	public static void main(String[] args) {
		System.out.println("new Integer(2) == 2 ?"+ (new Integer(2) == 2) );//java编译器自动拆箱
		System.out.println("new Integer(2) == new Integer(2) ?"+ (new Integer(2) == new Integer(2)) );
		/**
		 * if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
            -128 to 127,当数字比较小为真
		 */
		System.out.println("Integer.valueOf(2) == Integer.valueOf(2) ?"+ (Integer.valueOf(2) == Integer.valueOf(2)) );
		
	}

}
