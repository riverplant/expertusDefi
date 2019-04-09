package com.example.java8.collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.example.java8.dto.Apple;
import com.example.java8.dto.Dish;
import com.example.java8.dto.Dish.Type;

/**
 * 
 * @author riverplant
 *
 */
public class CollectorsAction {
	// 创建menu
	private final static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
			new Dish("beef", false, 700, Type.MEAT), new Dish("chicken", false, 400, Type.MEAT),
			new Dish("french frits", true, 530, Type.OTHER), new Dish("rice", true, 350, Type.OTHER),
			new Dish("pizza", true, 550, Type.OTHER));

	public static void main(String[] args) {
		/**
		 * 1.averagingDouble(ToDoubleFunction<? super T> mapper)
		 */
		testAveragingDouble();
		/**
		 * 2.averagingInt(ToIntFunction<? super T> mapper)
		 */
		testAveragingInt();
		/**
		 * 3.averagingLong(ToLongFunction<? super T> mapper)
		 */
		testAveragingLong();
		/**
		 * 4.collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)
		 * 
		 */
		testCollectingAndThen();
		/**
		 * 5.counting() 统计list中的个数
		 */
		testCounting();
		/**
		 * 6.groupingBy(Function<? super T,? extends K> classifier)
		 */
		testGroupByFunction();
		/**
		 * 7.groupingBy(Function<? super T,? extends K> classifier .Collector<? super
		 * T,A,D> dwonstream)
		 */
		testGroupByFunctionAndCollect();
	}

	/**
	 * 将Dish::getCalories平均值转成double
	 */
	private static void testAveragingDouble() {
		Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	/**
	 * 将Dish::getCalories平均值转成int
	 */
	private static void testAveragingInt() {
		Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	/**
	 * 将Dish::getCalories平均值转成Long
	 */
	private static void testAveragingLong() {
		Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	/**
	 * 算平均值，然后通过Function<R, RR> finisher 将结果打印
	 * Collectors.collectingAndThen(Collector<T, A, R> downstream, Function<R, RR>
	 * finisher)
	 */
	private static void testCollectingAndThen() {
		Optional.ofNullable(menu.stream().collect(Collectors
				.collectingAndThen(Collectors.averagingLong(Dish::getCalories), a -> "The Average Calories is->" + a)))
				.ifPresent(System.out::println);

		// 可以修改的ist
		menu.stream().filter(i -> i.getType().equals(Dish.Type.MEAT)).collect(Collectors.toList())
				.add(new Dish("", false, 100, Type.OTHER));
		// 通过collectingAndThen将list设置为不可以修改
		menu.stream().filter(i -> i.getType().equals(Dish.Type.MEAT))
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

	}

	/**
	 * 
	 */
	private static void testCounting() {
		Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
		;
	}

	/**
	 * 根据Type进行分类
	 */
	private static void testGroupByFunction() {
		Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
		;
	}

	/**
	 * 根据Type进行分类,然后再求出每一种类型对应的个数 Function<? super Dish, ? extends Type> classifier,
	 * Collector<? super Dish, Object, Long> downstream
	 */
	private static void testGroupByFunctionAndCollect() {
		Optional.ofNullable(menu.stream()
				// .collect(Collectors.groupingBy(Dish::getType,Collectors.counting())))//按照类型分组，然后统计个数
				.collect(Collectors.groupingBy(Dish::getType, Collectors.averagingDouble(Dish::getCalories))))
				// 按照类型分组，然后获得该类型的Calories平均值
				.ifPresent(System.out::println);
	}

	/**
	 * groupingBy(Function<? super Dish, ? extends Type> classifier,
	 * Supplier<TreeMap<Type, Double>> mapFactory, Collector<? super Dish, Object,
	 * Double> downstream) 通过第二个参数Supplier<TreeMap<Type, Double>>
	 * mapFactory可以设置返回的map类型
	 */
	private static void testGroupByFunctionAndSupplierAndCollect() {
		TreeMap<Type, Double> treeMap = menu.stream().collect(
				Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingDouble(Dish::getCalories)));
		// Optional.ofNullable(menu.stream()
		// .collect(Collectors.groupingBy(Dish::getType,Collectors.counting())))//按照类型分组，然后统计个数
		// .collect(Collectors.groupingBy(Dish::getType,TreeMap::new,Collectors.averagingDouble(Dish::getCalories))))
		// 按照类型分组，然后获得该类型的Calories平均值
		// .ifPresent(System.out::println);
	}

	/**
	 * 获得list中Dish::getCalories的所有统计数据:count,sum,max,min,average
	 */
	private static void testSummarizingInt() {
		IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		Optional.ofNullable(result).ifPresent(System.out::println);
	}

	/**
	 * 
	 * Collectors.groupingByConcurrent( Function<? super Dish,? extends Type>
	 * classifier)
	 */
	private static void testGroupingByConcurrentWithFunction() {
		ConcurrentMap<Dish.Type, List<Dish>> map = menu.stream()
				.collect(Collectors.groupingByConcurrent(Dish::getType));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	/**
	 * groupingByConcurrent( Function<? super Dish, ? extends Type> classifier,
	 * Collector<? super Dish, Object, Double> downstream)
	 */
	private static void testGroupingByConcurrentWithFunctionAndCollector() {
		ConcurrentMap<Dish.Type, Double> map = menu.stream()
				.collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingDouble(Dish::getCalories)));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	/**
	 * groupingByConcurrent( Function<? super Dish, ? extends Type> classifier,
	 * Supplier<ConcurrentSkipListMap<Type, Double>> mapFactory,
	 * //定义返回的ConcurrentSkipListMap Collector<? super Dish, Object, Double>
	 * downstream)
	 */
	private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
		ConcurrentMap<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType,
				ConcurrentSkipListMap::new, Collectors.averagingDouble(Dish::getCalories)));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	/**
	 * join:将名字连接,传入参数delimiter进行分割
	 */
	private static void testJoining() {
		menu.stream().map(Dish::getName).collect(Collectors.joining("||"));
	}

	/**
	 * 
	 * joining( CharSequence delimiter, CharSequence prefix, CharSequence suffix)
	 */
	private static void testJoiningWithPrexAndSuffix() {
		menu.stream().map(Dish::getName).collect(Collectors.joining("||", "prex", "suffix"));
	}

	/**
	 * mapping(Function<? super Dish, ? extends String> mapper, Collector<? super
	 * String, ?, String> downstream)
	 */
	private static void testMapping() {
		menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",")));
	}

	/**
	 * 
	 * maxBy(Comparator<? super Dish> comparator)
	 */
	private static void testMaxBy() {
		menu.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories)))
				.ifPresent(System.out::println);
		;
	}

	/**
	 * 
	 * minBy(Comparator<? super Dish> comparator)
	 */
	private static void testMinBy() {
		menu.stream().collect(Collectors.minBy(Comparator.comparingDouble(Dish::getCalories)))
				.ifPresent(System.out::println);
		;
	}

	/**
	 * 对元素进行分区(Partition)，返回一个Map<Boolean, List<Dish>>
	 */
	private static void testPartitioningByWithPredicate() {
		Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	/**
	 * 对元素进行分区(Partition)，返回一个Map<Boolean, List<Dish>> 将结果交给Collector<? super Dish,
	 * Object, Double> downstream进行下一步处理 .partitioningBy(Predicate<? super Dish>
	 * predicate, Collector<? super Dish, Object, Double> downstream)
	 */
	private static void testPartitioningByWithPredicateAnd() {
		Map<Boolean, Double> map = menu.stream()
				.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingDouble(Dish::getCalories)));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	/**
	 * 获得最大calorie
	 */
	private static void testReducingBinaryOperator() {
		menu.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingDouble(Dish::getCalories))))
				.ifPresent(System.out::println);
		;
	}

	/**
	 * reducing(Integer identity, BinaryOperator<Integer> op)
	 */
	private static void testReducingBinaryOperatorAndIdentify() {
		Integer result = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
	}

	/**
	 * reducing(Integer identity, Function<? super Dish, ? extends Integer> mapper,
	 * BinaryOperator<Integer> op)
	 */
	private static void testReducingBinaryOperatorAndIdentifyAndFunction() {
		Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
	}

	/**
	 * Summarizing
	 */
	private static void testSummarizingDouble() {
		Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
				.ifPresent(System.out::print);

	}

	/**
	 * Summarizing
	 */
	private static void testSummarizingLong() {
		Optional.ofNullable(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
				.ifPresent(System.out::print);

	}

	/**
	 * Summarizing
	 */
	private static void testCollectorSummarizingInt() {
		Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
				.ifPresent(System.out::print);

	}

	/**
	 * summingDouble(ToDoubleFunction<? super Dish> mapper)
	 */
	private static void testSummingDouble() {
		Optional.ofNullable(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
				.ifPresent(System.out::print);

		// menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum();
		menu.stream().map(Dish::getCalories).map(i -> {
			double d = i;
			return d;
		}).mapToDouble(Double::doubleValue).sum();
	}

	/**
	 * summingInt(ToIntFunction<? super Dish> mapper)
	 */
	private static void testSummingInt() {
		Optional.ofNullable(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
				.ifPresent(System.out::print);

	}

	/**
	 * summingLong(ToLongFunction<? super Dish> mapper)
	 */
	private static void testSummingLong() {
		Optional.ofNullable(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
				.ifPresent(System.out::print);
	}

	/**
	 * 
	 * toConcurrentMap(Function<? super Dish, ? extends String> keyMapper,
	 * Function<? super Dish, ? extends Integer> valueMapper) 
	 * 数据结构为ConcurrentHashMap
	 */
	private static void testToCollectionMap() {
		Optional.ofNullable(menu.stream().filter(i -> i.getCalories() > 600)
				.collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories))).ifPresent(System.out::print);
	}

	/**
	 * Type:Total
	 * 
	 * toConcurrentMap(Function<? super Dish, ? extends Type> keyMapper, Function<?
	 * super Dish, ? extends Long> valueMapper, BinaryOperator<Long>mergeFunction)
	 * V->1L:一个类型给一个值1作为个数,表示一个key值Dish::getType对应一个value:1,
	 * (a,b)->a+b:将相同的key值对应的value值加起来
	 * 数据结构为ConcurrentHashMap
	 */
	private static void testToConcurrentMapWithBinaryOperator() {
		Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
				.ifPresent(v -> System.out.println(v));
	}

	/**
	 * toConcurrentMap(Function<? super Dish, ? extends Type> keyMapper, 
	 * Function<? super Dish, ? extends Long> valueMapper, 
	 * BinaryOperator<Long> mergeFunction,
	 * Supplier<ConcurrentSkipListMap<Type, Long>> mapSupplier)//修改数据结构Hashmap转为ConcurrentSkipListMap
	 */
	private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
		Optional.ofNullable(menu.stream().collect(
				Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new)))
				.ifPresent(v -> System.out.println(v));
	}
	
	/**
	 * 
	 * toMap(Function<? super Dish, ? extends String> keyMapper,
	 * Function<? super Dish, ? extends Integer> valueMapper) 
	 * 数据结构为HashMap
	 */
	private static void testToMap() {
		//保证Collectors.toMap线程安全
		menu.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toMap(
								Dish::getName, Dish::getCalories), Collections::synchronizedMap));
//		Optional.ofNullable(menu.stream().filter(i -> i.getCalories() > 600)
//				.collect(Collectors.toMap(Dish::getName, Dish::getCalories))).ifPresent(System.out::print);
	}

	/**
	 * Type:Total
	 * 
	 * toMap(Function<? super Dish, ? extends Type> keyMapper, Function<?
	 * super Dish, ? extends Long> valueMapper, BinaryOperator<Long>mergeFunction)
	 * V->1L:一个类型给一个值1作为个数,表示一个key值Dish::getType对应一个value:1,
	 * (a,b)->a+b:将相同的key值对应的value值加起来
	 * 数据结构为HashMap
	 */
	private static void testToMapWithBinaryOperator() {
		
		menu.stream().collect(Collectors.collectingAndThen(
				Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b), Collections::synchronizedMap));
//		Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
//				.ifPresent(v -> System.out.println(v));
	}

	/**
	 * toMap(Function<? super Dish, ? extends Type> keyMapper, 
	 * Function<? super Dish, ? extends Long> valueMapper, 
	 * BinaryOperator<Long> mergeFunction,
	 * Supplier<ConcurrentSkipListMap<Type, Long>> mapSupplier)//修改数据结构Hashmap(非线程安全)转为Hashtable(线程安全)
	 */
	private static void testToMapWithBinaryOperatorAndSupplier() {
		Optional.ofNullable(menu.stream().collect(
				Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new)))
				.ifPresent(v -> System.out.println(v));
	}
}
