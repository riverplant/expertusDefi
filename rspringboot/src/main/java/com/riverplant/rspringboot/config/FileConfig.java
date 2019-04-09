package com.riverplant.rspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/jdbc.properties")
//@PropertySource("file:/e/jdbc.properties") 可以指定多个配置文件，包括classpath:config和file:
//@PropertySources({@PropertySource("classpath:config/jdbc.properties"),@PropertySource("file:/e/jdbc.properties")})
public class FileConfig {

}
