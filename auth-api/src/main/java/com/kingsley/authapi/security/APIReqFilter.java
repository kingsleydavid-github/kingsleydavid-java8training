package com.kingsley.authapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsley.authapi.utils.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;

//@Component
public class APIReqFilter extends OncePerRequestFilter {

	private static final String HEADER_AUTHORIZATION = "Authorization";

	@Autowired
	AuthUserDetailsService authUserDetailsServices;

	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
    ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader(HEADER_AUTHORIZATION);
		String username = null;
		String jwt = null;

		try {
			if (authHeader != null && authHeader.startsWith("Bearer")) {
				jwt = authHeader.substring(7);
				username = jwtUtil.extractUsername(jwt);
			}
	
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	
				UserDetails userDetails = this.authUserDetailsServices.loadUserByUsername(username);
					if (jwtUtil.validateToken(jwt, userDetails)) {
						
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
			}
			filterChain.doFilter(request, response);
		} catch(ExpiredJwtException ex) {
			String isRefreshToken = request.getHeader("isRefreshToken");
			String requestURL = request.getRequestURL().toString();
			// allow for Refresh Token creation if following conditions are true.
			if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
				allowForRefreshToken(ex, request);
				filterChain.doFilter(request, response);
			} else
				request.setAttribute("exception", ex);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}

}
