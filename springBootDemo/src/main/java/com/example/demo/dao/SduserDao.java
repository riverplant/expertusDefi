package com.example.demo.dao;

import com.example.demo.bean.Sduser;

public interface SduserDao {

	int inset(Sduser sdUser);
	
	int deleteById(int id);
	
	int updateById(Sduser sdUser);
	
	Sduser selectById(int id);
}
