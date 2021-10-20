package com.example.springloadbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbonlb")
@RibbonClient(name = "ribbonmicro1")
public class RibbController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/ribbon")
	public ResponseEntity<?> test(){
		
		String url = "http://ribbonmicro1/student/test";
		String port = restTemplate.getForObject(url, String.class);
		
		return ResponseEntity.ok("Test OK @ port :: "+ port);
	} 
	
}
