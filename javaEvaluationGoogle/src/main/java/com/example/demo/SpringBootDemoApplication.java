package com.example.demo;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jms.annotation.EnableJms;

import com.example.boot.spring4.beans.Dog;
import com.example.boot.spring4.beans.Jeep;
import com.example.boot.spring4.beans.MyBean;
import com.example.boot.spring4.beans.testBean;
import com.example.boot.spring4.beans.factory.RunnableFactoryBean;
import com.example.boot.spring4.componet.AnnotationScan;
import com.example.boot.spring4.config.MySpringConfig;
import com.example.boot.spring4.impl.MyContextAwareImpl;

/**
 * extends SpringBootServletInitializer:相当于web.xml
 * @author riverplant
 *
 */
@EnableJms//启用ActiveMQ
@EnableCaching//该注解用于启动缓存
@ServletComponentScan//该注解用于注入filter\servlet\lisentner
@SpringBootApplication
public class SpringBootDemoApplication extends SpringBootServletInitializer{
//	@Bean//在这里注册MyServlet
//	public ServletRegistrationBean<MyServlet> servletRegistrationBean() {
//		return new ServletRegistrationBean<MyServlet>(new MyServlet(),"/river");
//	}
//	
//	@Bean//在这里注册MyFilter
//	public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
//		//只拦截MyServlet
//		//return new FilterRegistrationBean<MyFilter>(new MyFilter(),servletRegistrationBean());
//		//拦截全部请求
//		return new FilterRegistrationBean<MyFilter>(new MyFilter());
//	}
//	
//	@Bean//在这里注册MyFilter
//	public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean() {
//		//拦截MyServlet
//		//return new FilterRegistrationBean<MyFilter>(new MyFilter(),servletRegistrationBean());
//		//拦截全部请求
//		return new ServletListenerRegistrationBean<MyListener>(new MyListener());
//	}
	/**
	 * builder.sources(SpringBootDemoApplication.class)
	 * 相当于配置
	 * 适合3.0以上
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootDemoApplication.class);
	}
	public static void main(String[] args) {
		//将配置类作为参数传入
//		AnnotationConfigApplicationContext context = 
//				new AnnotationConfigApplicationContext(MySpringConfig.class);
		//通过扫描包的方式一次性加载
//		AnnotationConfigApplicationContext context = 
//				new AnnotationConfigApplicationContext("com.example.boot.spring4");
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AnnotationScan.class);
//		MyBean myBean = context.getBean(MyBean.class);//通过类获取
//		MyBean myBeanByName = (MyBean) context.getBean("mybean");//通过名字获取
//		context.getBean(RunnableFactoryBean.class);//使用该方法获取的是FactoryBean本身
//		context.getBean("createRunnableFactoryBean");//使用该方法获取的是FactoryBean创建出来的Runnable
//		context.getBean("&createRunnableFactoryBean");//使用该方法获取的是FactoryBean本身
//		context.getBean(Jeep.class);//当创建Jeep的时候，会默认将作为参数的JeepFactory创建
//		context.getBean(Dog.class);
		context.getBean(MyContextAwareImpl.class).toString();
		context.getBeansOfType(testBean.class).values().forEach(System.out::println);;
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(testBean.class);
		builder.addPropertyValue("id", "testId");
		builder.addPropertyValue("name", "testerRoot");
		context.registerBean(testBean.class, builder.getBeanDefinition());//也可以通过AnnotationConfigApplicationContext注册bean
		context.close();
		
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}

