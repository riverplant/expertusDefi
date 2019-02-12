package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.SdUserLog;
/**
 * @param <T> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 * @author riverplant
 *
 */
//public interface SdUserLogDao extends MongoRepository<SdUserLog, Integer>使用方法完全相同
public interface SdUserLogDao extends JpaRepository<SdUserLog, Integer>{
	@Query(value="from SdUserLog sdu where sdu.userName=?1")//写了注解后会优先执行，hql语句?需要改成jpa-style
	List<SdUserLog> findByUserName(String name);
	
	SdUserLog findOne(Integer id);
	
	SdUserLog findByUserIp(String name);
	//JPA内置解析方式,不需要写实现方法，自动实现
	SdUserLog findByUserIpAndUserName(String ip,String name);

	Page<SdUserLog> findByUserName(String string, Pageable pageable);
}
