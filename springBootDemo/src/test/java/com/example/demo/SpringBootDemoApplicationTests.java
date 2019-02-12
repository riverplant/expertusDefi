package com.example.demo;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.bean.SdUserLog;
import com.example.demo.bean.Sduser;
import com.example.demo.component.RedisComponent;
import com.example.demo.controller.IndexController;
import com.example.demo.dao.SdUserLogDao;
import com.example.demo.dao.SdUserLogMongoDao;
import com.example.demo.dao.SduserDao;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
	@Autowired
	private RedisComponent redisComponent;
	@Autowired
	private SduserDao sduserDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SdUserLogDao sduserLogDao;
	@Autowired
	private SdUserLogMongoDao sdUserLogMongoDao;
	
    private MockMvc mvc;
//    @Autowired
//	private WebApplicationContext wac;
    @Before
    public void before() {
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();// setup mvc
    	 mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }
    @Test
    public void set() {
    	redisComponent.set("riverplant", "hello,riverplant");
    }
    
    @Test
    public void get() {
    	System.out.println(redisComponent.get("riverplant"));
    }
//	@Test
//	public void contextLoads() throws Exception {
//		RequestBuilder req = get("/index");
//		mvc.perform(req)
//		.andExpect(status().isOk());
//		
//		//.andExpect((ResultMatcher) content().string("hello index"));
//	}
	@Test
	public void insert() {
		Sduser sduser = new Sduser();
		sduser.setName("测试01");
		sduser.setCreate_time(new Date());
		int result = sduserDao.inset(sduser);
		System.out.println(result);//打印1：代表数据库改动的条数
	}
	
	@Test
	public void deleteById() {
		int result = sduserDao.deleteById(2);
		System.out.println(result);
	}
	
	@Test
	public void updateById() {
		Sduser sduser = sduserDao.selectById(2);
		sduser.setName("测试05");
		sduser.setCreate_time(new Date());
		int result = sduserDao.updateById(sduser);
		System.out.println(result);
	}
	
	@Test
	public void query() {
		Sduser sduser = sduserDao.selectById(2);
		System.out.println(sduser);
	}
	@Test
	public void jpasave() {
		SdUserLog entity = new SdUserLog();
		entity.setUserName("riverplant");
		entity.setUserIp("127.0.0.1");
		entity.setCreateTime(new Date());
		sduserLogDao.save(entity);
	}
	
	@Test
	public void jpadelet() {
		sduserLogDao.findById(1).ifPresent(i->sduserLogDao.delete(i));;	
	}

	@Test
	public void jpaFindByUserName() {
		sduserLogDao.findByUserName("riverplant");
		
	}
	
	@Test
	public void mongoRepositoryUpdate() {
		SdUserLog entity = new SdUserLog();
		entity.setId(1);//设置要更新的记录id
		entity.setCreateTime(new Date());
		sdUserLogMongoDao.save(entity);//通过save方法来更新,如果该id有元素，就是更新，如果该id为空就是创建
	}
	
	@Test
	public void queryForPage() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC,"id");
		Page<SdUserLog> result = sduserLogDao.findByUserName("riverplant",pageable);//有用户名为条件的分页查询
		Page<SdUserLog> result_all = sduserLogDao.findAll(pageable);//无复杂条件的分页查询
		System.out.println(result.getContent());
	}
	
	@Test
	public void testRegister() {
		String result = userService.register("jie", "192.168.0.54");
		System.out.println(result);
	}
	
	@Test
	public void testRegisterLogs() {
		String result = userService.registerLogs("jie", "192.168.0.54");
		System.out.println(result);
	}
}

