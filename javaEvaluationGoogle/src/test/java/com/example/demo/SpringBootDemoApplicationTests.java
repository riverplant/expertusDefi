package com.example.demo;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
	
	
	
	
    private MockMvc mockMvc;
//    @Autowired
	private WebApplicationContext wac;
    @Before
    public void before() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();// setup mvc
    	 
    }
   
}

