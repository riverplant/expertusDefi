package com.jyx.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jyx.dto.User;
import com.jyx.dto.UserQueryCondition;

/**
 * @RestController:该类提供restAPI
 * @author riverplant
 *
 */
@RestController
public class UserController {
	// public List<User> query(@RequestParam(required = false) String username) {
	//Pageable pageable:可以传递分页信息
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> query(UserQueryCondition condition,@PageableDefault(page = 1,size = 10,sort = "username,asc")Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
}
