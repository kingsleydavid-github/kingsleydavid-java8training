package com.kingsley.orders.handler;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsley.orders.model.error.ErrorResponse;
import com.kingsley.orders.security.AuthUserDetailsService;
import com.kingsley.orders.utils.JWTUtil;

import io.jsonwebtoken.SignatureException;

@Configuration
public class OrdersHandlerInterceptor implements HandlerInterceptor {


	private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
	private static final String TOKEN_INVALID = "TOKEN_INVALID";

	private static final String URL_VALIDATE_TOKEN = "http://localhost:3000/validateToken";
	private static final String URL_REFRESH_TOKEN = "http://localhost:3000/refreshtoken";

	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String AUTH_HEADER_PREFIX_BEARER = "Bearer";

	@Autowired
	AuthUserDetailsService authUserDetailsServices;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ObjectMapper objectMapper;

	
	public class IMCustomHttpServletRequest extends HttpServletRequestWrapper{

		 private final Map<String, String> customHeaders;
		
		public IMCustomHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.customHeaders = new HashMap<String, String>();
			// TODO Auto-generated constructor stub
		}
		public void putHeader(String name, String value){
	        this.customHeaders.put(name, value);
	    }

	    public String getHeader(String name) {
	        String headerValue = customHeaders.get(name);

	        if (headerValue != null){
	            return headerValue;
	        }
	        return ((HttpServletRequest) getRequest()).getHeader(name);
	    }

	    public Enumeration<String> getHeaderNames() {
	        Set<String> set = new HashSet<String>(customHeaders.keySet());

	        @SuppressWarnings("unchecked")
	        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
	        while (e.hasMoreElements()) {
	            String n = e.nextElement();
	            set.add(n);
	        }
	        return Collections.enumeration(set);
	    }
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// return HandlerInterceptor.super.preHandle(request, response, handler);
		String authResourceUrl = URL_VALIDATE_TOKEN;
		boolean isAuthenticated = false;
		String authHeader = request.getHeader(HEADER_AUTHORIZATION);
		String username = null;
		String jwt = null;

		try {
			if (authHeader != null && authHeader.startsWith(AUTH_HEADER_PREFIX_BEARER)) {
				jwt = authHeader.substring(7);
				ResponseEntity<String> resp = restTemplate.postForEntity(authResourceUrl, jwt, String.class);

				if (resp.getStatusCode().equals(HttpStatus.OK)) {
					authenticateRequest(request, jwt);
					isAuthenticated = true;
				} else {
					if (resp.getStatusCode().equals(HttpStatus.UNAUTHORIZED)
							&& resp.getBody().equals(TOKEN_EXPIRED)) {
						// token expired
						// call refreshtoken with exxtracted jwt
						String refreshedJwt = getRefreshedToken(jwt);
						
						IMCustomHttpServletRequest r = new IMCustomHttpServletRequest(request);
						r.putHeader(HEADER_AUTHORIZATION, "Bearer "+ jwt);
						isAuthenticated = true;
						// setAuthentication
					} 
					if (resp.getStatusCode().equals(HttpStatus.UNAUTHORIZED)
							&& resp.getBody().equals(TOKEN_INVALID)) {
						isAuthenticated = false;
						throw new SignatureException(TOKEN_INVALID);
					}
				}
			}

		} catch (SignatureException se) {
			isAuthenticated = false;
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), "Invalid Token");
			response.setContentType("application/json");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		}
		return isAuthenticated = false;
	}

	private String getRefreshedToken(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HEADER_AUTHORIZATION, "Bearer " + jwt);
		headers.add("isRefreshToken", "true");
		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<Map> respObj = restTemplate.exchange(URL_REFRESH_TOKEN, HttpMethod.GET, req, Map.class);

		Map<Object, Object> refreshTokenResp = respObj.getBody();
		/// get new token and
		String refreshedJwt = (String) refreshTokenResp.get("authToken");
		return refreshedJwt;
	}

	private void authenticateRequest(HttpServletRequest request, String jwt) {
		String username;
		username = jwtUtil.extractUsername(jwt);

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
	}
}
