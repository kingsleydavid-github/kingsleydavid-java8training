package com.cmp.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loader")
public class LoaderController {

	@GetMapping("/process")
	public String process() {
		return "Start processing the Data Loader";
	}
}
