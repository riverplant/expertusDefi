package com.riverplant.expertus.defi.config;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.ListenerRetry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.riverplant.expertus.defi.interceptor.MyTimeInterceptor;
/**
 * 
 * @author riverplant
 *
 */
//@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	@Autowired
	private MyTimeInterceptor myTimeInterceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myTimeInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/login","/ref");;
        super.addInterceptors(registry);
    }
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // Spring data jpa pageable
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }
	
	@Bean
	public FilterRegistrationBean<Filter> characterEncodingFilterRegister() {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);
		registrationBean.setFilter(filter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		//ServletRegistrationBean<Servlet>
		return registrationBean;
	}
		
}
