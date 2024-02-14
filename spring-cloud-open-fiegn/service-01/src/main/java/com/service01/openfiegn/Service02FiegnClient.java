package com.service01.openfiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service02", url = "http://localhost:8082/service02")
public interface Service02FiegnClient {
	@GetMapping("/User/display")
	public String display();

}
