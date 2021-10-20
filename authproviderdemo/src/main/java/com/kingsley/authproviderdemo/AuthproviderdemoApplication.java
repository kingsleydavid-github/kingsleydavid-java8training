package com.kingsley.authproviderdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.kingsley.authproviderdemo.repo")
public class AuthproviderdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthproviderdemoApplication.class, args);
	}

}
