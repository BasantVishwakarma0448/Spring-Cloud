package com.service02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User")
public class UserCtl {
	@GetMapping("display")
	public String display() {
		return "this is display method from service-02...!!!";
	}

	@PostMapping("submit")
	public String submit() {
		return "this is submit method from service-02...!!!";
	}
}
