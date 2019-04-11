package com.riverplant.expertus.defi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
/**
 * 
 * @author riverplant
 *
 */
//@Component
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equalsIgnoreCase("riverplant")) {
			return new User("riverplant","{noop}123456",new ArrayList<GrantedAuthority>());
		}
		return null;
	}
	@Bean
	public UserDetailsService userDetailsService() {
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	UserDetails user = User.withUsername("user").password(encoder.encode("123456"))
	.roles("USER").build();
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	manager.createUser(user);
	return manager;

	}

}
