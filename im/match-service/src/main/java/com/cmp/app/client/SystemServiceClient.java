package com.cmp.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SYSTEMCONFIG-SERVICE")
public interface SystemServiceClient {

	@GetMapping("/system/decodeCountry")
	public String getDecodeCountries();

}
