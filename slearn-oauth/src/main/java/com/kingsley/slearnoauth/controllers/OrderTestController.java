package com.kingsley.slearnoauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class OrderTestController {
	
	@GetMapping("/test/{msg}")
	public ResponseEntity<String> testCall(@PathVariable String msg){
		return ResponseEntity.ok(msg);
	}

	@GetMapping("/demo/{msg}")
	public ResponseEntity<String> demoCall(@PathVariable String msg){
		return ResponseEntity.ok(msg);
	}
}
