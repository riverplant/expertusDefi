package com.river.web.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

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
 * @author riverplant
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsetControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();// setup mvc
	}

	@Test
	public void whenQuerySuccess() {
		try {
			String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
					// .param("size", "10")
					// .param("page", "1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1)).andReturn().getResponse()
					.getContentAsString();// jsonpath

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void whenGetInfoSuccess() {
		try {
			String result = mockMvc
					.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("admin")).andReturn().getResponse()
					.toString();// jsonpath

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenGetInfoFail() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
