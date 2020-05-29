package com.au.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/")
	public String helloWorld() {
		return "unristricted endpoint";
	}
	
	@GetMapping("/auth")
	public String restrictedEndpoint() {
		return "you are loggedin";
	}
	
}
