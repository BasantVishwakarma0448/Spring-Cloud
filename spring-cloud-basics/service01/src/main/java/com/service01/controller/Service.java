package com.service01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class Service {
	@GetMapping("display")
	public String display() {
		return "This is service01 inside display";
	}

	@PostMapping("submit")
	public String submit() {
		return "This is service01 inside submit";
	}

}
