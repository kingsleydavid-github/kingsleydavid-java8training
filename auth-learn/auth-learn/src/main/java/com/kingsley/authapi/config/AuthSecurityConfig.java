package com.kingsley.authapi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired AuthReqFilter authReqFilter;
	 */

	/*
	 * 
	 * Commenting this out because JdbcUserDetailManager used insead of HardCoded
	 * UserDetailService
	 * 
	 */
	// @Autowired
	// UserDetailsService authUserDetailsServices;

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.userDetailsService(authUserDetailsServices); }
	 * 
	 */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// ignore authentication for authenticate endpoint
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/authenticate", "/validateToken").permitAll()
				.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// filter all other requests
		// httpSecurity.addFilterBefore(authReqFilter,
		// UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * 
	 * Commenting this out because, custom userDetails is defined.
	 *
	 */
	@Bean
	public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
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
