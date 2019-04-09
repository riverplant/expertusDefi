package com.example.java8.parallel.outil.spliterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MySpliteratorText {
	private final String[] data;
	private String regix;
	public String getRegix() {
		return regix;
	}
	public void setRegix(String regix) {
		this.regix = regix;
	}
	public String[] getData() {
		return data;
	}
	public Stream<String> stream(){
		return StreamSupport.stream(new MySpliterator(), false);
	}
	public Stream<String> parallelstream(){
		return StreamSupport.stream(new MySpliterator(), true);
	}
	public MySpliteratorText(String text, String regix) {
		Objects.requireNonNull(text, "The parameter text can not be null");
		this.data = text.split(regix);
	}
	/**
	 * 自定义Spliterator
	 * @author riverplant
	 *
	 */
	public class MySpliterator implements Spliterator<String> {
		private int start, end;
	
		/**
		 * 无参构造函数
		 */
		public MySpliterator() {
			this.start = 0;
			this.end = data.length - 1;
		}

		/**
		 * 有参构造函数
		 * 
		 * @param start
		 * @param end
		 */
		public MySpliterator(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		@Override // 不管是并行还是费非并行，只要有元素就开始消费
		public boolean tryAdvance(Consumer<? super String> action) {
			if (start <= end) {// 元素是有的
				action.accept(data[start++]);// 消费当前元素
				return true;
			}
			return false;
		}

		@Override // 通过递归实现分而治之,将数组分开的功能 ,取中间值
		public Spliterator<String> trySplit() {
			int mid = (end - start) / 2;
			if (mid <= 0) {// 开始值与结尾值相同,不需要继续拆分spliterator
				return null;
			}
			// 如果还需要继续拆分
			int left = start;
			int right = start + mid;// 此处为拆分的第一部分的最后一个元素
			start = right + 1;// 重置start为拆分后第二部分的第一个元素
			return new MySpliterator(left, right);// 将拆分出来的spliterator返回
		}

		@Override//还有多少个元素
		public long estimateSize() {
			// TODO Auto-generated method stub
			return end-start;
		}
		@Override//当元素值已知，返回
		public long getExactSizeIfKnown() {
			// TODO Auto-generated method stub
			return estimateSize();
		}
		
		@Override//特征值设置为不可变的,大小固定的,可以取子集的
		public int characteristics() {
			// TODO Auto-generated method stub
			return IMMUTABLE|SIZED|SUBSIZED;
		}

	}
}
