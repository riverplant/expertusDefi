package com.example.demo.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.common.Node;

public class ProgramDemo {

	/**
	 * recursive:递归
	 */
	public void recursiveDemo() {

	}

	/**
	 * 创建链表 Create a linked list 末节点指向null 先建2,3,5..链表，然后把1与之接起来
	 * 
	 * @param values,返回链表的首节点
	 * @return head of the linked list.The returned linked list ends with last node
	 *         with getNext()==null.
	 */
	public <T> Node<T> CreateLinkedList(List<T> data) {
		System.out.println("进入方法");
		if (data.isEmpty()) {
			return null;
		}
		Node<T> firstNode = new Node<T>(data.get(0));// 1,2,3,4
		// Node headOfSublist = CreateLinkedList(data.subList(1, data.size()));
		// 一层一层的返回，最终生成链表!!!!!!!!!!!
		firstNode.setNext(CreateLinkedList(data.subList(1, data.size())));
		return firstNode;
	}
	
	

	/**
	 * 链表翻转 1->2->3->null 3->2->1->null
	 */
	public <T> Node<T> enverseLinkedlist(List<T> data) {
		System.out.println("进入方法");
		if (data.isEmpty()) {
			return null;
		}
		// 取出最后一个作为第一个
		Node<T> lastNode = new Node<T>(data.get(data.size() - 1));
		// Node laseSecondNode = enverseLinkedlist(data.subList(0, data.size()-1));
		// 递归取出后面的所有节点
		// 将节点连接
		lastNode.setNext(enverseLinkedlist(data.subList(0, data.size() - 1)));

		return lastNode;
	}

	public <T> Node<T> enverseLinkedlistByNode(Node<T> node) {
		if (node == null) {// size==0
			return null;
		}
		if (node.getNext() == null) {// size==1
			return node;
		}
		Node<T> newHead = enverseLinkedlistByNode(node.getNext());
		node.getNext().setNext(node);
		node.setNext(null);
		return newHead;
	}

	/**
	 * 使用循环: newHead:指向反转成功的链表 currentHead:指向还没有反转的链表
	 * 
	 * @param node
	 * @return
	 */
	public <T> Node<T> enverseLinkedlistByLoop(Node<T> node) {

		if (node.getNext() == null) {// size==1
			return node;
		}

		Node<T> newHead = null;
		Node<T> curHead = node;
		// 1.Loop invariant
		// newHead points to the linked list already reversed
		// curHead points to the linked list not yet reversed
		// 考虑已经循环了很多轮，下一轮怎么做
		while (curHead != null) {
			Node<T> next = curHead.getNext();// next = null
			curHead.setNext(newHead); // curHead reversed
			newHead = curHead;// 右移一位 //newHead points to last node
			curHead = next;// 维持原循环不变式 //curHead = null
			// loop invariant holds
		}
		// loop invariant holds
		return newHead;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public <T> Node<T> CreateLargeLinkedList(List<T> data) {
		//data.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
		if(data.isEmpty())return null;
		Node<T> newHead = null;
		int index = 0;
		Node<T> curNode = new Node<T>(data.get(index));
		while (index < (data.size())) {
			Node<T> next = null;
			if (index != (data.size() - 1)) {
				next = new Node<T>(data.get(index + 1));
			} 
			curNode.setNext(newHead);
			newHead = curNode;
			curNode = next;
			index++;
		}
		return newHead;
	}
	public static void main(String[] args) {
		ProgramDemo demo = new ProgramDemo();
		// Node headOfSublist = demo.enverseLinkedlist(Arrays.asList(1,2,3,4));
		// Node headOfSublist = demo.CreateLinkedList(Arrays.asList(1,2,3,4));
		// Node head = demo.enverseLinkedlistByNode(headOfSublist);
		//Node node = demo.deletenumberfromLinkedList(demo.CreateLargeLinkedList(Arrays.asList(2,2,1, 2,2,2, 3, 2,5,4)), 2);
		//System.out.println(node);
		//List list = Arrays.asList(1,2,3,4,5);
		
		int index = demo.binarysearch(Arrays.asList(1,2,3,4,5,6,6,7), 10);
        System.out.println(index);
	}
	/**
	 * 
	 * @param data:data a sorted array
	 * @param val
	 * @return
	 */
	public int Binarysearch(int[] data,int val) {
		int a=0;//指向头
		int b=data.length;//指向尾  [a,b)+[b,c)=[a,c)   [a,a)=empty
		//Loop invariant [a,b) is a valid range(a<=b)
		//val may only be within range[a,b)
		while(a<b) {
			//if(a == b) return -1;//空集
			int m = (a + b) / 2;
			//b == a+1 : m=a
			//b == a+2 : m=a+1
			if(val < data[m]) {
				b = m; //b)
			}else if(val>data[m]) {
				a = m + 1;//[a
			}else {
				return m;
			}
		}
		return -1;
	}
	
	public int BinarysearchFromList(List<Integer> data,int val) {
		int a=0;//指向头
		int b=data.size();//指向尾  [a,b)+[b,c)=[a,c)   [a,a)=empty
		//Loop invariant [a,b) is a valid range(a<=b)
		//val may only be within range[a,b)
		while(a<b) {
			//if(a == b) return -1;//空集
			int m = (a + b) / 2;
			if(data.get(m) > val) {
				b = m;
			}else  if(data.get(m) < val){
				a=m+1;
			}else {
				return m;
			}
		}
		return -1;
	}
	/**
	 * 有序数组
	 * 二分查找
	 * 计算a,b的中间点
	 * 
	 */
	public int binarysearch(List<Integer> data,int val) {
		if(data.size()<=0) return -1;
		int index=data.size()/2;
		int realIndex = data.size()/2;
		while(data.size()>0 ) {
			if((data.size()==1 && data.get(0) !=val) || realIndex<0) {
				return -1;
			}
			if(data.get(realIndex)==val) {
				break;
			}
			if((data.get(data.size()/2))>val) {
				data = data.subList(0, data.size()/2+1);
				index -=data.size()/2;
				realIndex = index;
			}else {
				data = data.subList(data.size()/2,data.size() );
				index +=data.size()/2;
				realIndex = data.size()/2;
			}	
		}
		return index;
	}	
	/**
	 * 
	 * @param head
	 * @param value
	 * @return
	 */
	public <T> Node<T> deleteIfEquals(Node<T> head , int value) {
		if(head ==null) {
			return null;
		}
		while(head!=null && (Integer)head.getValue() == value) {//如果连续出现2
			head = head.getNext();
		}
		Node<T> prev = head;//假设从头开始到prev已经被完全处理了
		while(prev.getNext() != null) {
			//prev.getNext() == null
			if((Integer)prev.getNext().getValue() == value) {
				prev.setNext(prev.getNext().getNext());//删除2的节点
			}else {
				prev = prev.getNext();
			}
		}
		return head;
	}
	/**
	 * 
	 * @param node
	 * @param num
	 * @return
	 */
	public <T> Node<T> deletenumberfromLinkedList(Node<T> node , int num) {
		List<T> nodes = new ArrayList<T>();
		int index=0;
		while(node != null) {
			nodes.add(index, node.getValue());
			node = node.getNext();
			index++;
		}
		List<Node<T>> new_nodes = nodes.stream().filter(i->(Integer)i!=num)
		              .collect(Collectors.toList())
		               .stream().map(i->{return new Node<T>(i);})
		               .collect(Collectors.toList());
		for(int i=0; i<new_nodes.size();i++) {
			if(i!=(new_nodes.size()-1)) {
				new_nodes.get(i).setNext(new_nodes.get(i+1));
			}
		}	
		return new_nodes.get(0);
	}
	/**
	 * 
	 * @param size
	 * @return
	 */
	public <T> Node<T> CreateLargeLinkedList2(int size) {
		Node<T> prev = null;//已建立的linklist的最后一个节点
		Node<T> head = null;
		for(int i=1;i<=size;i++) {
			Node<T> node  = new Node(i);
			if(prev!=null) {
				prev.setNext(node);
			}else {
				head = node;
			}
			prev = node;
		}
		return head;
	}

	/**
	 * combinations([1,2,3,4],2) 列出所有组合
	 * 
	 * @param data
	 * @param n
	 *            取出1，只需要从剩下的数字里取一个 不取1，需要从剩下的数字里取两个 Generates all combinations and
	 *            output them, select n elements from data
	 */
	public void combinations(List<Integer> selected, List<Integer> data, int n) {
		/**
		 * 1.initial value for recursion 2.how to select elements 3.how to output
		 */
		if (n == 0) {
			System.out.print("n=0");
			System.out.println();
			// output all selected elements
			for (Integer i : selected) {
				System.out.print(i);
				System.out.print(" ");
			}
			System.out.println();
			return;
		}
		if (data.isEmpty()) {
			return;
		}

		// 选择element 0 的情况
		selected.add(data.get(0));
		combinations(selected, data.subList(1, data.size()), n - 1);// 当n=1打印return,执行下面

		// 不选element 0的情况
		selected.remove(selected.size() - 1);
		combinations(selected, data.subList(1, data.size()), n);
		// 当data为empty的时候return,程序完成，重新返回selected.remove(selected.size()-1);
		// 把1删除,此时data = data.subList(1, data.size())，将会从2开始取值

	}

}
