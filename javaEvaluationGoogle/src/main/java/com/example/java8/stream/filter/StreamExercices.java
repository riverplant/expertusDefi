package com.example.java8.stream.filter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.dto.Trader;
import com.example.java8.dto.Transaction;

/**
 * Stream联系
 * 
 * @author riverplant
 *
 */
public class StreamExercices {
	static Trader mario = new Trader("Mario", "Milan");
	static Trader alan = new Trader("Alan", "Cambridge");
	static Trader brian = new Trader("Brian", "Cambridge");
	static Trader raoul = new Trader("Raoul", "Cambridge");
	static List<Transaction> transctions = Arrays.asList(new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
	public static void main(String[] args) {
		
		
		/**
		 * 1.Find all transactions in the year 2011 and sort them by value(small to
		 * high)
		 */
		//transctions.stream().filter(i->i.getYear()==2011).reduce((a,b)->a.getValue()<b.getValue()?a:b);
		/**
		 * 2.What are all the unique cities where the traders work?)
		 */
		//transctions.stream().map(i->i.getTrader().getCity()).distinct();
		
		/**
		 * 3.Find all traders from Cambridge and sort them by name)
		 */
//		transctions.stream()
//		.map(Transaction::getTrader)
//		.filter(i->i.getCity().equalsIgnoreCase("Cambridge"))
//		.distinct()
//		.sorted(Comparator.comparing(Trader::getName));
		/**
		 * 4.Return a string of all traders'names sorted alphabetically )
		 */
		transctions.stream()
		.map(transaction->transaction.getTrader().getName())
		.distinct()
		.sorted(String.CASE_INSENSITIVE_ORDER)
		.collect(Collectors.toList())
		.forEach(System.out::println);
		/**
		 * 5.Are any traders based in Milan? 
		 */
//		transctions.stream()
//		.filter(transaction->transaction.getTrader().getCity().equalsIgnoreCase("Milan"));
		
		/**
		 * 6.Print all transactions' values from the traders living in Cambridge. 
		 */
//		transctions.stream()
//		.filter(transaction->transaction.getTrader().getCity().equalsIgnoreCase("Cambridge"))
//		.map(transaction->transaction.getValue())
//		.forEach(System.out::println);
		/**
		 * 7.What's the highest value of all the transactions?
		 */
		double max = transctions.stream()
		.map(transaction->transaction.getValue())
		.mapToDouble(i->i)
		.max()
		.getAsDouble();
		System.out.println("max="+max);
		
		/**
		 * 8.Find the transaction with the smallest value.
		 */
		
		double min = transctions.stream()
				.map(transaction->transaction.getValue())
				.mapToDouble(i->i)
				.min()
				.getAsDouble();
		System.out.println("min="+min);
	}

}
