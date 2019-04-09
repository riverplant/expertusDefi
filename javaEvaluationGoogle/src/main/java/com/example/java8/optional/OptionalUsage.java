package com.example.java8.optional;

import java.util.Optional;

import com.example.java8.dto.Insurance;

/**
 * 对Object进行了包装
 * @author riverplant
 *
 */
public class OptionalUsage {
public static void main(String[] args) {
	//创建一个空的Optional
	Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();
	//通过该方法创建，不会出现空指针异常,对Insurance进行了封装
	Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
	//对以上两种方法的集合
	Optional<Insurance> objectOptional = Optional.ofNullable(new Insurance());
	//如果有值就返回，如果没有就调用Insurance::new
	objectOptional.orElseGet(Insurance::new);//当参数为Supplier，可以使用函数推导
	objectOptional.orElse(new Insurance());//当参数为一个对象，直接使用new
	objectOptional.orElseThrow(RuntimeException::new);
	objectOptional.orElseThrow(()-> new RuntimeException("exception message...."));
	
	Insurance insurance = objectOptional.filter(i->i.getName()==null).get();
}
/**
 * 
 * @param insurance
 * @return
 */
private static String getInsuranceNameByOptional(Insurance insurance) {
   return Optional.ofNullable(insurance)
	        .map(i->i.getName())
	        .orElse("unknown");
	        
}
}
