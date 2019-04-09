package com.example.demo.oop.compagne;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.example.demo.common.Node;
/**
 * 
 * @author riverplant
 * 只能有一个public class
 */
public class MyLinkedList<T> implements Iterable<T> {// 实现了Iterable接口就可以实现for循环
	private Node<T> head;// 头结点
	private Node<T> tail;// 尾节点
	//<T>属于类的一部分，所以必须申明<T> MyLinkedList<T>
	//static函数必须对T进行申明 public static <T> MyLinkedList<T> newEmptyList()
	public static <T> MyLinkedList<T> newEmptyList() {
		return new MyLinkedList<T>();
	}

	/**
	 * 构造函数，head初始化为null,tail初始化为null
	 */
	public MyLinkedList() {
		head = null;
		tail = null;
	}

	public void add(T value) {
		Node<T> node = new Node<>(value);
		if (tail == null) {// 当前无节点，新建的节点又是头部，又是尾部
			head = node;// 更新头部节点
		} else {
			tail.setNext(node);// 将原尾部接在新建的node上
		}
		tail = node;// 更新尾部
	}
    /**
     * 该类用于实现Iterator接口
     * @author riverplant
     * 因为MyInterator包在MyLinkedList里面，所以不需要再写T了
     */
	private class MyInterator implements Iterator<T> {
       private Node<T> currentNode;
        public MyInterator(Node<T> head) {//构造函数负责初始化
        	currentNode = head;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentNode!=null;
		}
		/**
	     * Returns the next element in the iteration.
	     *
	     * @return the next element in the iteration
	     * @throws NoSuchElementException if the iteration has no more elements
	     */
		@Override
		public T next() {
			if(currentNode == null) {
				throw new NoSuchElementException();
			}
			T value =  currentNode.getValue();
			currentNode = currentNode.getNext();
			// TODO Auto-generated method stub
			return value;
		}
	}

	@Override//返回一个Iterator
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new MyInterator(head);
	}

}
