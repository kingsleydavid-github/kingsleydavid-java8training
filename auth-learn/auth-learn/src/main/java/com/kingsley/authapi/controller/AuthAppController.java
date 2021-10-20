package com.kingsley.authapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.authapi.model.auth.AuthRequest;
import com.kingsley.authapi.utils.JWTUtil;

import io.jsonwebtoken.SignatureException;

@RestController("/")
public class AuthAppController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	//private UserDetailsService authUserDetailService;
	private JdbcUserDetailsManager authUserDetailService;

	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/hello/{msg}")
	public @ResponseBody ResponseEntity<String> testHello(@PathVariable String msg) {
		if (msg != null) {
			return ResponseEntity.ok(msg);
		} else {

			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<?> autheticate(@RequestBody AuthRequest authRequest) throws Exception {

		ResponseEntity<?> respEntity  = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			final UserDetails userDetails = authUserDetailService.loadUserByUsername(authRequest.getUsername());
			final String token = jwtUtil.generateToken(userDetails);
			
			respEntity = ResponseEntity.ok(token);
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		return respEntity;
	}

	@PostMapping("/validateToken")
	public @ResponseBody ResponseEntity<?> validateToken(@RequestBody String token) {

		ResponseEntity<?> respEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		try {
			final UserDetails userDetails = authUserDetailService.loadUserByUsername(jwtUtil.extractUsername(token));
			boolean isTokenValid = jwtUtil.validateToken(token, userDetails);
			if (isTokenValid) {
				respEntity = ResponseEntity.ok().body("Valid");
			} else {
				respEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		} catch (SignatureException se) {
			// TODO: change to loggers
			System.out.println(se.getMessage());
		}
		return respEntity;
	}
}
