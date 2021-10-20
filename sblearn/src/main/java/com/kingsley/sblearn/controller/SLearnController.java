package com.kingsley.sblearn.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SLearnController {

	@GetMapping("/tst")
	public ResponseEntity<String> testController (@PathVariable String a ){
		return ResponseEntity.ok("Hello !");
	}
}
