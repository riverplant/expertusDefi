package com.riverplant.expertus.defi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 
 * @author riverplant
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.withUsername("user").password(encoder.encode("123456")).roles("USER").build();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(user);
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
               http.httpBasic()
                   .and()
		          .formLogin()
		          .loginPage("/login.html")
		         .loginProcessingUrl("/auth")
		          .and()
		          .csrf().disable()
		          .authorizeRequests()
				.antMatchers("/login.html").permitAll()
				.antMatchers("/auth").permitAll()
				.antMatchers("/js/**", "/css/**", "/images/*", "/fonts/**", "/**/*.png", "/**/*.jpg").permitAll()
				.anyRequest().authenticated();
	}
}
