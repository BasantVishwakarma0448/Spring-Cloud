package com.service01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service01.feignclient.Service02FeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("User")
public class UserCtl {
	@Autowired
	private Service02FeignClient service02FeignClient;

	@GetMapping("display")
	@CircuitBreaker(name = "service02Breaker", fallbackMethod = "service02FallBack")
	public String display() {
		return service02FeignClient.display();
	}

	@PostMapping("submit")
	public String submit() {
		return "this is submit method from service-01...!!!";
	}

	public String service02FallBack(Throwable throwable) {
		System.out.println("Fallback is executed because service is down : " + throwable.getMessage());
		return "Fallback Response : Service is under maintenance";
	}
}
