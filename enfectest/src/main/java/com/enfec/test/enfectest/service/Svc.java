package com.enfec.test.enfectest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enfec.test.enfectest.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Svc {

	@Autowired
	RestTemplate restTemplate;

	@Value("${users.endpoint}")
	String userUrl;

	@Autowired
	ObjectMapper mapper;

	public List<User> getFilteretedUsers() {

		List<User> filtered  = new ArrayList<>();
		ResponseEntity<String> allUsers = restTemplate.getForEntity(userUrl, String.class);

		User[] users;
		try {
			users = mapper.readValue(allUsers.getBody(), User[].class);
			List<User> usrs = Arrays.asList(users);

			Predicate<User> p1 = (u) -> u.getEmail().startsWith("s") || u.getEmail().startsWith("S");
			filtered = usrs.stream().filter(p1).collect(Collectors.toList());

			return filtered;

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filtered;

	}

}
