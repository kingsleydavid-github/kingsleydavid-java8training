package com.kingsley.orders.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderDetailsController {
	
	final static String orderPerm1 = "'READ','WRITE','UPDATE'";
	final static String orderPerm2 = "'READONLY'";
	
	@PreAuthorize("hasAnyAuthority("+ orderPerm1 +")")
	@GetMapping("/{orderId}")
	public @ResponseBody ResponseEntity<String> getOrder(@PathVariable String orderId, HttpServletRequest req){
			return ResponseEntity.ok(orderId);
	}

	@PreAuthorize("hasAuthority("+ orderPerm2 +")")
	@GetMapping("/readOnly/{orderId}")
	public @ResponseBody ResponseEntity<String> getReadOnlyOrder(@PathVariable String orderId, HttpServletRequest req){
		return ResponseEntity.ok(orderId);
	}

}
