package com.kingsley.orderhistory.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kingsley.orderhistory.model.OrderHistory;

@Service
public class OrderHistoryService {

	
	private static final String HEADER_PREFIX_BEARER = "Bearer";
	private static final String GET_ORDERS_URL = "http://localhost:7000/orders/1";
	private static final String HEADER_AUTHORIZATION = "Authorization";
	
	@Autowired
	RestTemplate restTemplate;

	public OrderHistory getOrderHistory(HttpServletRequest request, String userId) {

		OrderHistory hist = new OrderHistory();
		
		String authHeader = request.getHeader(HEADER_AUTHORIZATION);
		String jwt = authHeader.substring(7);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HEADER_AUTHORIZATION, HEADER_PREFIX_BEARER + " " + jwt);
		
		HttpEntity<String> req = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> resp = restTemplate.exchange(GET_ORDERS_URL, HttpMethod.GET, req, String.class);
			hist = new OrderHistory("1", "12132", "test");
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return hist;
	}
}
