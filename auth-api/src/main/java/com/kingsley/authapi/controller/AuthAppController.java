package com.kingsley.authapi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.authapi.model.AuthRequest;
import com.kingsley.authapi.model.AuthResponse;
import com.kingsley.authapi.model.error.AuthValidityResponse;
import com.kingsley.authapi.security.AuthUserDetailsService;
import com.kingsley.authapi.utils.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;

@RestController("/")
public class AuthAppController {

	private static final String UNAUTHORIZED = "UNAUTHORIZED";
	private static final String TOKEN_VALID = "TOKEN_VALID";
	private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
	private static final String TOKEN_INVALID = "TOKEN_INVALID";
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthUserDetailsService authUserDetailService;

	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/getUserName/{username}")
	public @ResponseBody ResponseEntity<String> testHello(@PathVariable String username) {
		if (username != null) {
			return ResponseEntity.ok(username);
		} else {

			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<?> autheticate(@RequestBody AuthRequest authRequest) throws Exception {

		final UserDetails userDetails = authUserDetailService.loadUserByUsername(authRequest.getUsername());

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
					authRequest.getPassword(), userDetails.getAuthorities()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}

		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/validateToken")
	public @ResponseBody ResponseEntity<?> validateToken(@RequestBody String token) {

		ResponseEntity<?> respEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		try {
			final UserDetails userDetails = authUserDetailService.loadUserByUsername(jwtUtil.extractUsername(token));
			boolean isTokenValid = jwtUtil.validateToken(token, userDetails);
			if (isTokenValid) {
				AuthValidityResponse successResponse = new AuthValidityResponse(HttpStatus.OK.toString(), TOKEN_VALID );
				respEntity = ResponseEntity.ok().body(successResponse);
			} else {
				AuthValidityResponse unAuthResponse = new AuthValidityResponse(HttpStatus.UNAUTHORIZED.toString(), UNAUTHORIZED);
				respEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unAuthResponse);
			}
		} catch (ExpiredJwtException se) {
			AuthValidityResponse errorResponse = new AuthValidityResponse(HttpStatus.UNAUTHORIZED.toString(), TOKEN_EXPIRED );
			respEntity = ResponseEntity.ok().body(errorResponse);
		} catch (SignatureException se) {
			AuthValidityResponse errorResponse = new AuthValidityResponse(HttpStatus.UNAUTHORIZED.toString(),TOKEN_INVALID );
			respEntity = ResponseEntity.ok().body(errorResponse);
			System.out.println(se.getMessage());
		}
		return respEntity;
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthResponse(token));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}
