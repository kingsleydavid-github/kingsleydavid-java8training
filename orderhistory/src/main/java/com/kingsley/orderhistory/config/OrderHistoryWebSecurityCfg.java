package com.kingsley.orderhistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kingsley.orderhistory.filter.APIReqFilter;
import com.kingsley.orderhistory.security.AuthUserDetailsService;

@Configuration
@EnableWebSecurity
public class OrderHistoryWebSecurityCfg extends WebSecurityConfigurerAdapter {

	@Autowired
	APIReqFilter orderHistAuthFilter;
	
	@Autowired
	AuthUserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder pwdEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				//.antMatchers("/orders/*").hasAnyAuthority("USER", "ADMIN")
				//.antMatchers("/demo/*").hasAuthority("USER")
				.anyRequest().authenticated().and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(orderHistAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
}
