package com.service01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service01.openfiegn.Service02FiegnClient;

@RestController
@RequestMapping("User")
public class UserCtl {
	@Autowired
	private Service02FiegnClient service02FiegnClient;

	@GetMapping("display")
	public String display() {
		return service02FiegnClient.display();

	}
	@PostMapping("submit")
	public String submit() {
		return "this is submit method from service01...!!!";
	}
}
