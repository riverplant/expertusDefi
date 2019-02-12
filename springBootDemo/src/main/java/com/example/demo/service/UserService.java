package com.example.demo.service;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.SdUserLog;
import com.example.demo.bean.Sduser;
import com.example.demo.dao.SdUserLogDao;
import com.example.demo.dao.SduserDao;

/**
 * 逻辑层
 * 
 * @author riverplant
 *
 */
@Service
public class UserService {
	@Autowired
	private SduserDao suserDao;
	@Autowired
	private SdUserLogDao sdUserLogDao;
	
	private  Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * 用户注册
	 * @param name
	 * @param ip
	 * @return
	 * Propagation propagation() default Propagation.REQUIRED：传播行为默认
	 * readOnly:只读取数据库不更新，可以帮助数据库引擎优化事物
	 * rollbackFor:遇到指定异常强制回滚
	 * noRollbackFor:遇到指定异常强制不回滚
	 * rollbackForClassName:遇到指定多个异常时强制回滚，可以指定多个异常类名
	 * noRollbackForClassName:遇到指定多个异常时强制不回滚，可以指定多个异常类名
	 * Isolation isolation() default Isolation.DEFAULT;隔离默认
	 */
	@Transactional//该事物控制如果方法运行中间出线异常，将会回滚数据库
	public String register(String name, String ip) {
     //1.添加用户
		Sduser sduser = new Sduser();
		boolean flag = false;
		String result="";
		StringBuffer sb=new StringBuffer();
		sduser.setName(name);
		sduser.setCreate_time(new Date());
		try {
			suserDao.inset(sduser);
			logger.info("创建用户"+name+"成功");
			result = "创建用户"+name+"成功";
			flag = true;
		} catch (Exception e) {
			logger.info("创建用户"+name+"失败");
			result = "创建用户"+name+"失败";
			throw new RuntimeException("");
		}
		result =sb.append(result).toString();
		if(flag) {//如果此时抛出异常，之前的用户添加会回滚
			SdUserLog sdUserLog = new SdUserLog();
			sdUserLog.setCreateTime(new Date());
			sdUserLog.setUserIp(ip);
			sdUserLog.setUserName(name);
			try {
				sdUserLogDao.save(sdUserLog);
				logger.info("创建日志"+name+"成功");
				result = sb.append("|").append("创建日志"+name+"成功").toString();
			} catch (Exception e) {
				e.printStackTrace();
				result = sb.append("|").append("创建日志"+name+"失败").toString();
			}
			
		}//if
		
		return result;
	}

	
	@Transactional//该事物控制如果方法运行中间出线异常，将会回滚数据库
	public String registerLogs(String name, String ip) {

		String result="";
		StringBuffer sb=new StringBuffer();
			SdUserLog sdUserLog = new SdUserLog();
			sdUserLog.setCreateTime(new Date());
			sdUserLog.setUserIp(ip);
			sdUserLog.setUserName(name);
			try {
				sdUserLogDao.save(sdUserLog);
				logger.info("创建日志"+name+"成功");
				result = sb.append("|").append("创建日志"+name+"成功").toString();
			} catch (Exception e) {
				e.printStackTrace();
				result = sb.append("|").append("创建日志"+name+"失败").toString();
			}		
		return result;
	}
}
