package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Harddisk;

@RestController
public class ComputerController {
	
	@Autowired
	private Computer computer;
	@GetMapping("/computers")
	public Computer getComputers() {
		return computer;
	}
	
	@Autowired
	private Harddisk harddisk;
	@GetMapping("/harddisks")
	private Harddisk getHarddisks() {
		return harddisk;
	}

}
