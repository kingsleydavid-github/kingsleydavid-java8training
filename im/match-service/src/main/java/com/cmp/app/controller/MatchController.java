package com.cmp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmp.app.client.SystemServiceClient;

@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private SystemServiceClient client;
	
	@GetMapping("/lookup")
	public String lookup() {
		client.getDecodeCountries();
		return "Lookup for the given input request";
	}
}
