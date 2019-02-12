package com.example.demo.ehcache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.SdUserLog;
import com.example.demo.dao.SdUserLogDao;
import com.example.demo.ehcache.SdUserLogCache;
@CacheConfig(cacheNames = "riverCache")
@Repository//该注解代表持久层
public class SdUserLogCachImpl implements SdUserLogCache {
	@Autowired
	private SdUserLogDao sdUserLogDao;
	
    @Cacheable(key = "#p0")//先从缓存中读取数据，如果没有再调用方法获取数据，然后加入缓存
	@Override
	public SdUserLog selectById(Integer id) {
		//进入该方法就证明缓存中没有
    	System.out.println("缓存中没有，直接读库,id="+id);
		return sdUserLogDao.findOne(id);
	}
    @CachePut(key = "#p0")//每次都触发真是方法，然后进行缓存，适用于更新和插入
	@Override
	public SdUserLog updateById(SdUserLog sdUserLog) {
		// TODO Auto-generated method stub
		return sdUserLogDao.save(sdUserLog);
	}
    @CacheEvict(key = "#p0")//根据一定条件对缓存进行清空，适用于删除
	@Override
	public String deletById(Integer id) {
		// TODO Auto-generated method stub
		return "清空缓存成功";
	}

}
