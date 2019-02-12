package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.bean.Sduser;
import com.example.demo.dao.SduserDao;
import com.example.demo.jdbcTemplate.JdbcDaoImpl;
/**
 * jdbcTemplet模板
 * @author riverplant
 *
 */
@Repository//该注解代表持久层
public class SduserDaoImpl extends JdbcDaoImpl implements SduserDao {
    @Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int inset(Sduser sdUser) {
		String sql = "insert into sduser (name,create_time) value (?,?)";
		
		return jdbcTemplate.update(sql, sdUser.getName(),sdUser.getCreate_time());
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from sduser where id=?";
		return jdbcTemplate.update(sql,id);
	}

	@Override
	public int updateById(Sduser sdUser) {
		String sql = "update sduser set name=?, create_time=? where id=?";
		return jdbcTemplate.update(sql,sdUser.getName(),sdUser.getCreate_time(),sdUser.getId());
	}

	@Override
	public Sduser selectById(int id) {
		String sql = "select * from sduser where id=?";
//		return jdbcTemplate.queryForObject(sql, new RowMapper<Sduser>() {
//
//			@Override
//			public Sduser mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Sduser user = new Sduser();
//				user.setId(rs.getInt("id"));
//				user.setName(rs.getString("name"));
//				user.setCreate_time(rs.getDate("create_time"));
//				return user;
//			}
//			
//		},id);
		return queryForObject(sql, Sduser.class, id);//使用封装后的方法
	}

}
