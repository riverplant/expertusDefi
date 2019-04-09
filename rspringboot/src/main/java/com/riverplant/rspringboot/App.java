package com.riverplant.rspringboot;

import java.util.Arrays;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import com.riverplant.rspringboot.beans.User;
import com.riverplant.rspringboot.config.MyConfig;
import com.riverplant.rspringboot.config.TomcatProperties;
import com.riverplant.rspringboot.events.MyApplicationEvents;
import com.riverplant.rspringboot.impl.MyApplicationContextInitializer;
import com.riverplant.rspringboot.impl.MyImportSelector;
import com.riverplant.rspringboot.interfaces.annotations.EnableLog;
import com.riverplant.rspringboot.listener.MyApplicationListener;

/**
 * Hello world!
 * @EnableConfigurationProperties:用来启用一个特性的，该特性为可以把配置文件中的属性注入到bean里
 */
@SpringBootApplication(scanBasePackages="com.riverplant.rspringboot")
//@EnableAutoConfiguration
//@ComponentScan
@EnableAsync//启用异步
@ServletComponentScan
public class App 
{
    public static void main( String[] args )
    {
 /**   	SpringApplication app = new SpringApplication(App.class);
 //   	app.setBannerMode(Banner.Mode.OFF);//设置启动不打印标记
//    	ApplicationListener<MyApplicationEvents>  applicationListener = new MyApplicationListener();
//    	app.addListeners(applicationListener);
//    	app.addInitializers(new MyApplicationContextInitializer());
//    	ConfigurableApplicationContext context = app.run(args);
//    	context.publishEvent(new MyApplicationEvents(new Object()));
//    	context.stop();
//    	context.close();**/
    	SpringApplication.run(App.class, args);
    }
    
    
    
//    @Bean
//    CommandLineRunner initializeDatabase(EmployeeRepository employeeRepository) {
//        return (evt) -> {
//            employeeRepository.save(Arrays.asList(
//                    new Employee("John", "Smith", "onlinetest.acquisio+jsmith@gmail.com", 20000),
//                    new Employee("John", "Mills", "onlinetest.acquisio+jmills@gmail.com", 50000)
//            ));
//        };
//    }
}
