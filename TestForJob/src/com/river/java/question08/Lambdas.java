package com.river.java.question08;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * QUESTION 09: Lambdas
 * (https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
 * Using JDK8 Lambdas, add the code to transform the input file to apply those
 * rules. 1- Keep lines where the amount is greater than or equals to 50$ 2- Add
 * a Taxes column right after the Amount column, which is 15% of the Amount. 3-
 * Add a Total column right after the Taxes column, which is the sum of Amount
 * and Taxes. 4- Remove the ShoppingCartTitle columns. 1-保留金额大于或等于50 $的行
 * 2-在金额列之后添加税收列，即金额的15％。 3-在“税收”列之后添加“总计”列，即“金额”和“税金”的总和。
 * 4-删除ShoppingCartTitle列。
 * <p>
 * The input file contains those columns User,Amount,ShoppingCartTitle,NbItems
 * <p>
 * IMPORTANT: Add all missing javadoc and/or unit tests that you think is
 * necessary.
 */
public class Lambdas {
	public static void main(String[] args) throws IOException, URISyntaxException {
		Lambdas instance = new Lambdas();
		URI path = Thread.currentThread().getContextClassLoader().getResource("./carts.csv").toURI();
		// System.out.println(path);
		File input = new File(path);
		File output = new File("./carts_output.csv");
		output.delete();

		instance.convertCarts(input, output);

		// if (output.exists()) {
		// Files.lines(output.toPath()).forEachOrdered(line ->
		// System.out.println(line));
		// }
	}

	void convertCarts(File input, File output) throws IOException, URISyntaxException {
		// TODO: Insert your code here.
		Stream<String> file_contents = Files.lines(input.toPath());
		List<List<String>> file_list = file_contents.filter(i -> {
			String[] str = i.split(",");
			return Double.parseDouble(str[1]) > 50.00;
		}).map(i -> {
			String[] str = i.split(",");
			Double tax = Double.parseDouble(str[1]) * 0.15;
			Double sum = Double.parseDouble(str[1]) + tax;
			List<String> list = Arrays.stream(str).collect(Collectors.toList());
			list.add(2, String.valueOf(tax));
			list.add(3, String.valueOf(sum));
			list.remove(4);
			return list;
		}).collect(Collectors.toList());// map
		// output
		StringBuffer sb = new StringBuffer();
		BufferedWriter bfw = new BufferedWriter(new FileWriter(output));
		file_list.stream().forEach(row -> {
			row.stream().forEach(col -> {
				sb.append(col+",");
			});
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		});

		try {
			bfw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bfw != null)
					bfw.close();
				file_contents.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
