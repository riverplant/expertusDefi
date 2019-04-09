package com.example.java8.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 自定义Collector<T> implements Collector<T, List<T>,
 * List<T>>--->T:Stream中元素类型，List<T>容器，List<T>返回值
 * 
 * @author riverplant
 *
 * @param <T>
 */
public class MyListCollector<T> implements Collector<T, List<T>, List<T>> {
	private static Logger logger = LoggerFactory.getLogger(MyListCollector.class);
	private void log(final String log) {
		logger.info(Thread.currentThread().getName()+"-"+log);
	}
	/**
	 * Supplier<A> supplier();//提供者 ,创建一个容器
	 * --->return a new mutable result container
	 * --->container = collector.supplier.get();
	 */
	@Override
	public Supplier<List<T>> supplier() {
		log("Supplier");
		return ArrayList::new;
	}
    /**
     *  BiConsumer<A,T> accumulator();//累加器,容器
         ----->accumulator = collector.accumulator();
         ------>forEach(u ->accumulator.accept(container,u));
                    两个入参List<T>：container
                       T：  u
                 实现将传入的元素T加入到容器中List<T>
     */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		log("accumulator");
		return List::add;
	}
    /**
     * 将Fork -> Join后的结构合并入一个容器
     */
	@Override
	public BinaryOperator<List<T>> combiner() {
		log("combiner");
		return (list1,list2)->{
			list1.addAll(list2);
			return list1;
		};
	}

	@Override//最终返回的结果
	public Function<List<T>, List<T>> finisher() {
		log("finisher");
		return t->t;
	}

	@Override//特征值
	public Set<Characteristics> characteristics() {
		log("characteristics");
		return Collections.unmodifiableSet(
				EnumSet.of(
						Collector.Characteristics.IDENTITY_FINISH,Collector.Characteristics.CONCURRENT));
	}

}
