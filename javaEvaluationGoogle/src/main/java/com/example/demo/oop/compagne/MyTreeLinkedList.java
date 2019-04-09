package com.example.demo.oop.compagne;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.example.demo.program.tree.TreeNode;

/**
 * 待完成......
 * @author riverplant
 *
 */
public class MyTreeLinkedList implements Iterable<Integer>{
	TreeNode head;// 头结点
	TreeNode tail;// 尾节点

	/**
	 * 构造函数，head初始化为null,tail初始化为null
	 */
	public MyTreeLinkedList() {
		head = null;
		tail = null;
	}
	public void add(char value) {
		TreeNode node = new TreeNode(value);
		if (tail == null) {// 当前无节点，新建的节点又是头部，又是尾部
			head = node;// 更新头部节点
		} else if(tail.getLeft() == null){
			tail.setLeft(node);// 将原尾部左叶接在新建的node上
		} else {
			tail.setRight(node);// 将原尾部右叶接在新建的node上
		}
		tail = node;// 更新尾部
	}
	class myTreeIterator implements Iterator<Integer>{
		TreeNode current;
		myTreeIterator(TreeNode node){
			current = node;
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
			return current!=null;
		}
		/**
	     * Returns the next element in the iteration.
	     *
	     * @return the next element in the iteration
	     * @throws NoSuchElementException if the iteration has no more elements
	     */
		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new myTreeIterator(head);
	}
/**
 * 	
	public TreeNode searchNextNode(TreeNode N){
		if(N == null) return null;
		if(N.getRight() != null)return getFirst(N.getRight());//左->自己->右，如有有右，下一个节点就是右
		
		if(N.getParent().getLeft()==N) {
			return N.getParent();
		}else {
			while(N.getParent()!=null && N.getParent().getLeft()!=N) {
				N = N.getParent();
			}
			return N.getParent();
		}	
	}

 public TreeNode getFirst(TreeNode node) {
	 if(node == null) return null;
	 TreeNode curr = node;
	 while(curr.getLeft() != null) {
		 curr = curr.getLeft();
	 }
	 return curr;
 }
 */
	

}
