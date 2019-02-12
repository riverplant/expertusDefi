package com.example.demo.ehcache;

import com.example.demo.bean.SdUserLog;

/**
 * 缓存接口,从缓存中操作
 * @author riverplant
 *
 */
public interface SdUserLogCache {
/**
 * 查询
 * @param id
 * @return
 */
	SdUserLog selectById(Integer id);
	/**
	 * 更新
	 * @param sdUserLog
	 * @return
	 */
	SdUserLog updateById(SdUserLog sdUserLog);
	/**
	 * 删除-->清空缓存
	 * @param id
	 * @return
	 */
	String deletById(Integer id);
}
