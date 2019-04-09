package com.riverplant.rspringboot.impl;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Spring容器全部初始化完了之后的最后一个回调
 * @author riverplant
 *
 */
@Order(1)//用该注解来控制不同的CommandLineRunner执行的顺序，数字越小越先执行!!!!!!!!!!!
@Component
public class MyCommandLineRunnerImpl implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==========应用已经成功的启动=================");
		System.out.println("==========应用启动的时间为"+LocalDateTime.now().toString());
		System.out.println("应用已经启动，参数为:"+Arrays.asList(args) );
		System.out.println("应用已经启动，参数为:"+Arrays.deepHashCode(args) );
		System.out.println("应用已经启动，参数为:"+Arrays.stream(args));
	}

}
