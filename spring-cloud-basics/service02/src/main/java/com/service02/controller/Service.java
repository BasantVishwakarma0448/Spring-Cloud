package com.service02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class Service {

	@GetMapping("display")
	public String display() {
		return "this is service02 in display";
	}

	@PostMapping("submit")
	public String submit() {
		return "this is service02 in submit";
	}
}
