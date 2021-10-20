package com.kingsley.imauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.authapi.model.AuthRequest;
import com.kingsley.authapi.model.AuthResponse;
import com.kingsley.imauth.security.IMUserDetailsService;
import com.kingsley.imauth.util.JWTUtil;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class IMAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IMUserDetailsService imUserDetailService;

	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/test/{msg}")
	public @ResponseBody ResponseEntity<String> testAuth(@PathVariable String msg) {

		if (msg != null) {
			return ResponseEntity.ok(msg);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<?> autheticate(@RequestBody AuthRequest authRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}

		final UserDetails userDetails = imUserDetailService.loadUserByUsername(authRequest.getUsername());
		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token));
	}
}


