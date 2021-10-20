package com.kingsley.sblearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kingsley.sblearn.beans.TestBean;

@Configuration
public class AppConfig {

	@Bean(name = "tb1", initMethod = "initmd")
	//@Scope("prototype")
	public TestBean getTestBean1()
	{
		return new TestBean();
	}
}
