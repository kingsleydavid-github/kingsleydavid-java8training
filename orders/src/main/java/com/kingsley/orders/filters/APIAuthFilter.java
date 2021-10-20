package com.kingsley.orders.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsley.orders.model.error.ErrorResponse;
import com.kingsley.orders.security.AuthUserDetailsService;
import com.kingsley.orders.utils.JWTUtil;

import io.jsonwebtoken.SignatureException;

@Component
public class APIAuthFilter extends OncePerRequestFilter {

	private static final String UNAUTHORIZED = "UNAUTHORIZED";
	private static final String TOKEN_VALID = "TOKEN_VALID";
	private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
	private static final String TOKEN_INVALID = "TOKEN_INVALID";

	@Value("${url.validate.token}")
	String urlValidateToken;

	@Value("${url.refresh.token}")
	String urlRefreshToken;

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

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authValidationUrl = urlValidateToken;
		String authHeader = request.getHeader(HEADER_AUTHORIZATION);
		String username = null;
		String jwt = null;

		try {
			if (authHeader != null && authHeader.startsWith(AUTH_HEADER_PREFIX_BEARER)) {
				jwt = authHeader.substring(7);
				//ResponseEntity<String> resp = restTemplate.postForEntity(authValidationUrl, jwt, String.class);
				HttpEntity<String> req = new HttpEntity<>(jwt);
				ResponseEntity<String> resp = restTemplate.exchange(authValidationUrl, HttpMethod.POST, req, String.class);
				ErrorResponse validityResp = objectMapper.readValue(resp.getBody(), ErrorResponse.class);
				if (validityResp.getCode().equals(HttpStatus.OK.toString())
						&& validityResp.getMessage().equals(TOKEN_VALID)) {
					authenticateRequest(request, jwt);
					filterChain.doFilter(request, response);
				} else {
					if (validityResp.getCode().equals(HttpStatus.UNAUTHORIZED.toString())
							&& validityResp.getMessage().equals(TOKEN_EXPIRED)) {
						// token expired
						// call refreshtoken with exxtracted jwt
						String refreshedJwt = getRefreshedToken(jwt);
						authenticateRequest(request, refreshedJwt);
						filterChain.doFilter(request, response);
					} 
					if (validityResp.getCode().equals(HttpStatus.UNAUTHORIZED.toString())
							&& validityResp.getMessage().equals(TOKEN_INVALID)) {
						throw new SignatureException(TOKEN_INVALID);
					}
				}
			}
		}
		catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			System.out.println(ex.getLocalizedMessage());
		}
		catch(SignatureException se) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), "Invalid Token" );
			response.setContentType("application/json");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		}
	}
	
	private String getRefreshedToken(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HEADER_AUTHORIZATION, "Bearer " + jwt);
		headers.add("isRefreshToken", "true");
		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<Map> respObj = restTemplate.exchange(urlRefreshToken, HttpMethod.GET, req, Map.class);

		Map<Object, Object> refreshTokenResp = respObj.getBody();
		// get new token and
		String refreshedJwt = (String) refreshTokenResp.get("authToken");
		return refreshedJwt;
	}

	private void authenticateRequest(HttpServletRequest request, String jwt) {
		String username = jwtUtil.extractUsername(jwt);

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
