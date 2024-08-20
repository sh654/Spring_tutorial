package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Harddisk {
	@Value("20")
	private int capacity;
	@Autowired
	public Harddisk(int capacity) {
		super();
		this.capacity = capacity;
	}

	public Harddisk() {}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Harddisk [capacity=" + capacity + "]";
	}
	
	
}
