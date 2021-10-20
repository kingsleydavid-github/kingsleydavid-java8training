package com.kingsley.orderhistory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
  prePostEnabled = true, 
  securedEnabled = true, 
  jsr250Enabled = true)
public class OrderHistoryMthdSecurityCfg extends GlobalMethodSecurityConfiguration{

	/*
	 * 
	 * This config enables method level security
	 * 
	 * */
	
}
