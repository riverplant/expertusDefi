package com.example.java8.interfaces;
/**
 * 
 * @author riverplant
 * 自定义一个三个参数的Function
 */
@FunctionalInterface
public interface ThreeFunction<T,U,K,R> {

	R apply(T t,U u,K k);
}
