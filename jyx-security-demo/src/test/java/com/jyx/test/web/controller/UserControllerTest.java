package com.jyx.test.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author riverplant public final class SpringRunner extends
 *         SpringJUnit4ClassRunner 通过SpringRunner来运行测试用例
 * @SpringBootTest:申明该类为测试用例类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;// 伪造的环境，不会真正启动tomcat

	private MockMvc mockMvc;// 伪造mvc的环境

	@Before // 写了@Before注解的方法会在每一个测试用例执行之前去执行
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void whenQuerySuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user")// 使用MockMvcRequestBuilders模拟request请求
				.param("username", "jojo")
				.param("uuid", "user001")
				.param("password", "123456")
				.param("age", "5")
				.param("ageTo", "60")
				//指定分页信息，查询第三页，每页显示15条，按年龄的降序排序
				.param("size", "15").param("page", "3").param("sort", "age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8))//请求的内容格式
		        .andExpect(MockMvcResultMatchers.status().isOk())// 请求返回的状态码为200
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));// 解析返回的Json内容,该集合的长度为3
	}
}
