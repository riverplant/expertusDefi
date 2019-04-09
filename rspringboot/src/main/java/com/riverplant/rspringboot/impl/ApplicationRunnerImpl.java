package com.riverplant.rspringboot.impl;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 * 
 * @author riverplant
 * 与CommandLineRunner作用相同，只是传递的参数不同
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

	@Override//public interface ApplicationArguments:
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("应用已经启动，参数为:"+Arrays.asList(args.getSourceArgs()) );
		System.out.println("应用已经启动，参数为:"+Arrays.deepHashCode(args.getSourceArgs()) );
                 Arrays
				.stream(args.getSourceArgs()).forEach(System.out::println);
	}

}
