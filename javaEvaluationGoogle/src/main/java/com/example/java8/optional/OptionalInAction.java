package com.example.java8.optional;

import java.util.Optional;

import com.example.java8.dto.Person;
/**
 * 
 * @author riverplant
 *
 */
public class OptionalInAction {

	public static void main(String[] args) {
		
	}
	/**如果对象person中的属性是Optional<Car>
	 * Car中的属性是Optional<Insurance>
	 * 这里就应该使用Optional.flatMap
	 * @param person
	 * @return
	 */
	private static String getInsuranceNameByOptional(Person person) {
		return Optional.ofNullable(person)
		        .filter(i->i.getCar()!=null)
		        .map(i->i.getCar())
		        .filter(car->car.getInsurance()!=null)
		        .map(car->car.getInsurance().getName())
		        .orElse("unknown");
	}
}
