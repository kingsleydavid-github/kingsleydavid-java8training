package com.mphasis.kingsley.mphasisbookskingsley;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mphasis.kingsley.mphasisbookskingsley.repo.BookRepo;
import com.mphasis.kingsley.mphasisbookskingsley.service.BookService;

@SpringBootApplication
public class MphasisbooksKingsleyApplication {

	@Autowired
	RestTemplate restTemplate;
	
	public static void main(String[] args) {
		System.out.println("App started.... ");
		//MphasisbooksKingsleyApplication.loadBooks();
		SpringApplication.run(MphasisbooksKingsleyApplication.class, args);
		
	}

	@Bean
	public RestTemplate restTempate() {
		return new RestTemplate();
	}

}
