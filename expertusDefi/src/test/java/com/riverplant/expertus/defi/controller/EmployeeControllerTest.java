package com.riverplant.expertus.defi.controller;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.riverplant.expertus.defi.App;

/**
 * 
 * @author riverplant
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EmployeeControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees")
			   .param("page", "1")
			   .param("size", "15")
			   .param("sort", "id,desc")
			   .accept(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		       .andReturn()
		       .getResponse().getContentAsString();
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
			   .accept(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test1@gmail.com"))
		       .andReturn()
		       .getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/edccsdfdd")
			   .accept(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().is4xxClientError());	      
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/employees")
			   .content("{\"id\":4,\"firstName\":\"test4\",\"lastName\":\"defi\",\"email\":\"test4@gmail.com\",\"password\":123456,\"createdTime\":\"2019-04-10\"}")
			   .contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4)) .andReturn()
		       .getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.put("/employees")
			   .content("{\"id\":1,\"firstName\":\"newtest1\",\"lastName\":\"defi\",\"email\":\"newtest1@gmail.com\",\"password\":123456,\"createdTime\":\"2019-04-10\"}")
			   .contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4)) .andReturn()
		       .getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		       mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1")
			   .contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().isOk());	       
	}
	
	@Test
	public void whenCookieOrHeaderExists() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
				.cookie(new Cookie("token","123456"))
				.header("auth", "xxxxxxxxxxx")
			   .accept(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(MockMvcResultMatchers.status().is4xxClientError());	      
	}

}
