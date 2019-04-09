package com.example.java8.stream;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CreateStream {

	public static void main(String[] args) {
		Stream.of("hello", "alex", "world");
		Arrays.stream(new String[] { "hello", "alex", "world" });
		

	}
   /**
    * 通过文件创建!!!!!
    * @return
    */
	private static Stream<String> createStreamFromFile() {
		String Filepath = "";
		Path path = Paths.get(Filepath);
		try (Stream<String> lines = Files.lines(path)) {
			return lines;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
	 * @return
	 */
	private static Stream<Integer> createStreamFromIterator() {
		//seed:初始值,n从初始值开始进行一元操作
		//UnaryOperator<T> f:接收一个参数T的一元操作符
		Stream<Integer> stream = Stream.iterate(0, n->n+2).limit(20);//创建出无限的
		return stream;
	}
	/**
	 * generate(Supplier<Double> s)
	 * @return
	 */
	private static Stream<Double> createStreamFromGenerator() {
	
		//generate(Supplier<Double> s) 使用 R get()来生成
		//Stream<Double> stream = Stream.generate(()->Math.random()).limit(10);
		Stream<Double> stream = Stream.generate(Math::random).limit(10);
		return stream;
	}
	/**
	 * 创建自己定义的Obj的Stream,使用自定义的ObjSupplier
	 * @return
	 */
	private static Stream<Obj> createObjStreamFromGenerator(){
		Stream<Obj> stream = Stream.generate(new ObjSupplier()).limit(10);
		return stream;
	}
	/**
	 * 构建Obj对象的Supplier为Generate方法使用
	 * 实现Supplier<Obj>接口
	 * @author riverplant
	 *
	 */
	static class ObjSupplier implements Supplier<Obj>{
        private int index = 0;
        private Random  random = new Random(System.currentTimeMillis());
		@Override
		public Obj get() {
			index = random.nextInt(100);
			return new Obj(index,"name->"+index);
		}		
	}
	/**
	 * 构建一个对象Obj
	 * @author riverplant
	 *
	 */
	static class Obj{
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Obj(int id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Obj [id=" + id + ", name=" + name + "]";
		}
		
	}
}
