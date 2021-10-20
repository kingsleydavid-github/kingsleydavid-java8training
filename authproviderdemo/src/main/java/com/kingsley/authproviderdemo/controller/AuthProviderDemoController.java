package com.kingsley.authproviderdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.authproviderdemo.model.auth.AuthRequest;
import com.kingsley.authproviderdemo.security.AuthUserDetailsService;
import com.kingsley.authproviderdemo.util.JWTUtil;

@RestController("/")
public class AuthProviderDemoController {
	
	@Autowired
	AuthenticationManager authnMgr;
	
	@Autowired
	//JdbcUserDetailsManager userDetailsService;
	AuthUserDetailsService userDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	
	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception{
		ResponseEntity<?> respEntity  = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
		try {
			authnMgr.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
			final String token = jwtUtil.generateToken(userDetails);
			
			respEntity = ResponseEntity.ok(token);
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		return respEntity;
		
	}

}
