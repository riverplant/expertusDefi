package com.riverplant.rspringboot.interview.java.multiThreads;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(1,2,3,7,8,9,10,4,5,6);
		arr = sord(arr);
		System.out.println(arr.toString());
		search (arr,8);
	}
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static List<Integer> sord(List<Integer> arr) {
	 arr.stream().sorted((i,j)->Integer.compare(i, j));
	 return arr;
	}
	public static void search(List<Integer> arr,int sum) {
		int start=0,end=arr.size()-1,index=0,count=0;
		for(int i=0;i<arr.size();i++) {
			count++;
			index = (end+start)/2;
			if(i==arr.size()-1) {
				System.out.println("经过第"+count+"次查找，无法找到"+sum);
			}else if(arr.get(i)>sum) {//小于中间值
				end = index;
			}else if(arr.get(i)<sum) {//大于中间值
				start = index;
			}else {
				System.out.println("第"+count+"次查找到"+sum+"该值位于"+i);
				break;
			}
		}
		
	}
}
