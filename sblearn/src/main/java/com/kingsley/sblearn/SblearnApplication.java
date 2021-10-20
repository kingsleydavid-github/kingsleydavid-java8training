package com.kingsley.sblearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kingsley.sblearn.beans.TestBean;
import com.kingsley.sblearn.config.AppConfig;

@SpringBootApplication
public class SblearnApplication {

	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(SblearnApplication.class, args);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		TestBean tb1 = (TestBean) context.getBean("tb1");
		TestBean tb2 = (TestBean) context.getBean("tb1");
		
		System.out.println(tb1.helloTestBean());
		System.out.println(tb2.helloTestBean());
	}
}
