package com.example.demo.jdbcTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * jdbcTemplate工具类
 * 
 * @author riverplant
 *
 */
public class JdbcDaoImpl {

	protected JdbcTemplate jdbcTemplate;

	/**
	 * 获取当前事物最后一次更新的主键值
	 * 插入之后返回主键
	 * @return
	 */
	public Long getLastId() {
		String sql = "select last_insert_id() as id ";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	/**
	 * public static void hasText(@Nullable String text, String message) { if
	 * (!StringUtils.hasText(text)) { throw new IllegalArgumentException(message); }
	 * }
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return <T> T
	 *         org.springframework.jdbc.core.JdbcTemplate.queryForObject(String sql,
	 *         RowMapper<T> rowMapper, @Nullable Object... args) throws
	 *         DataAccessException 获得对象信息
	 */
	public <T> T queryForObject(String sql, Class<T> clazz, Object... args) {
		Assert.hasText(sql, "sql语句不能为空");
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), args);
	}
/**
 * 分页1	
 * @param sql
 * @param pageCurrent
 * @param pageSize
 * @param args
 * @return
 */
public Page<Map<String,Object>> queryForPage(String sql,int pageCurrent,int pageSize,Object...args){
	Assert.hasText(sql, "sql语句不能为空");
	Assert.isTrue(pageCurrent>1,"pageNo必须大于1");
	String sqlCount = Sql.countSql(sql);
	int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);
	pageCurrent = Sql.checkPageCurrent(count,pageSize,pageCurrent);
	pageSize = Sql.checkPageSize(pageSize);
	int totalPage = Sql.countTotalPage(count,pageSize);
	String sqlList = sql+Sql.limitSql(count,pageCurrent,pageSize);
	List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlList,args);
	return new Page<Map<String,Object>> (count,totalPage,pageCurrent,pageSize,list);
	
}
/**
 * 分页
 * @param sql
 * @param pageCurrent
 * @param pageSize
 * @param classz
 * @param args
 * @return
 */
public <T> Page<T> queryForPage(String sql,int pageCurrent,int pageSize,Class<T>classz,Object...args){
	Assert.hasText(sql, "sql语句不能为空");
	Assert.isTrue(pageCurrent>1,"pageNo必须大于1");
	Assert.isTrue(classz!=null,"classz不能为空");
	String sqlCount = Sql.countSql(sql);
	int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);
	pageCurrent = Sql.checkPageCurrent(count,pageSize,pageCurrent);
	pageSize = Sql.checkPageSize(pageSize);
	int totalPage = Sql.countTotalPage(count,pageSize);
	String sqlList = sql+Sql.limitSql(count,pageCurrent,pageSize);
	List<T> list = jdbcTemplate.query(sqlList,new BeanPropertyRowMapper<T>(classz),args);
	return new Page<T> (count,totalPage,pageCurrent,pageSize,list);
	
}
	/**
	 * 获得对象集合信息
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public <T> List<T> queryForObjectList(String sql, Class<T> clazz, Object... args) {
		Assert.hasText(sql, "sql语句不能为空");
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(clazz));
	}

}
