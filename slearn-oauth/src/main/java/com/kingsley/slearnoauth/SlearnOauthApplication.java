package com.kingsley.slearnoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kingsley.slearnoauth.*")
public class SlearnOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlearnOauthApplication.class, args);
	}

}
