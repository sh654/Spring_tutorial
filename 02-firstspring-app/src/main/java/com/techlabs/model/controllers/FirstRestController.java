package com.techlabs.model.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
	
	/*
	@GetMapping("/helloduniya")
	public String sayHello() {
		return "Hello Welcome to Spring boot World !!";
	}
	*/
	
	@GetMapping("byeduniya")
	public String sayBye() {
		return "Bye mey chala";
	}
	
}
