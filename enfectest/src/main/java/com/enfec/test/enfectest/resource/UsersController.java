package com.enfec.test.enfectest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enfec.test.enfectest.model.User;
import com.enfec.test.enfectest.service.Svc;

@RestController
@RequestMapping("/")
public class UsersController {

	@Autowired
	Svc userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getFilteretedUsers());
	}
	
}
