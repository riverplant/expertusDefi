package com.example.demo.jdbcTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sql {

	public Sql() {

	}

	/**
	 * 防止sql注入
	 * 
	 * @param sql
	 * @return
	 */
	public static String checkSql(String sql) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (sql.indexOf(inj_stra[i]) >= 0) {
				return "";
			}
		}
		return sql;
	}

	/**
	 * 校验当前页数:先根据总记录数count和每页记录数pageSize来算出总页数totalpage
	 * 根据提交过来的当前页数pageCurrent是否大于totalpage，大于返回totalpage pageCurrent<1?1:pageCurrent
	 * 其它返回pageCurrent
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示记录数
	 * @param pageCurrent
	 *            当前页数
	 * @return
	 */
	public static int checkPageCurrent(int count, int pageSize, int pageCurrent) {
		int totalPage = countTotalPage(count, pageSize);
		if (pageCurrent > totalPage) {
			if (totalPage < 1) {
				return 1;
			}
			return totalPage;
		} else if (pageCurrent < 1) {
			return 1;
		} else {
			return pageCurrent;
		}
	}

	/**
	 * 计算当前分页的开始记录的索引
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	public static int countOffset(final int pageCurrent, final int pageSize) {
		return (pageCurrent - 1) * pageSize;
	}

	/**
	 * 移除sql中orderBy子句，用于分页前获取总记录数，不需要排序
	 * 
	 * @param sql
	 * @return
	 */
	public static String removeOrderBy(String sql) {
		Pattern pat = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher mc = pat.matcher(sql);
		StringBuffer strBuffer = new StringBuffer();
		while (mc.find()) {
			/**
			 * appendReplacement(StringBuffer sb, String replacement)
			 * 将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个 StringBuffer 对象里。
			 */
			mc.appendReplacement(strBuffer, "");
		}
		mc.appendTail(strBuffer);// 将最后一次匹配工作后剩余的字符串添加到一个 StringBuffer 对象里。
		return strBuffer.toString();
	}

	/**
	 * 根据分页查询语句获取统计总记录数的语句
	 * 
	 * @param sql
	 * @return
	 */
	public static String countSql(String sql) {
		String countSql = sql.substring(sql.toLowerCase().indexOf("from"));
		return "select count(*) " + removeOrderBy(countSql);
	}

	/**
	 * 根据总记录数，对页面传来的分页参数进行校验，返回分页的sql语句
	 * 
	 * @param count:
	 * @param pageCurrent:当前页
	 * @param pageSize:每页记录
	 * @return
	 */
	public static String limitSql(int count, int pageCurrent, int pageSize) {
		pageCurrent = checkPageCurrent(count, pageSize, pageCurrent);
		pageSize = checkPageSize(pageSize);
		return "limit " + countOffset(pageCurrent, pageSize) + "," + pageSize;
	}

	/**
	 * 校验每页记录数是否合法 1.当页面输入的每页记录数pageSize>最大记录数MX_PAGE_SIZE,返回MX_PAGE_SIZE
	 * 2.当页面输入的每页记录数pageSize<1 返回默认的DEFAULT_PAGE_SIZE
	 * 
	 * @param pageSize
	 * @return
	 */
	public static int checkPageSize(int pageSize) {
		if (pageSize > Page.MAX_PAGE_SIZE) {
			return Page.MAX_PAGE_SIZE;
		} else if (pageSize < 1) {
			return Page.DEFAULT_PAGE_SIZE;
		} else {
			return pageSize;
		}
	}

	/**
	 * 计算总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public static int countTotalPage(final int count, final int pageSize) {
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}
}
