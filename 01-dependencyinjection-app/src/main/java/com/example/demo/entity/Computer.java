package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Computer {
	@Value("Lenovo")
	private String name;
	@Autowired
	private Harddisk disk;
	public String getName() {
		return name;
	}
	
	
	public Computer(String name, Harddisk disk) {
		super();
		this.name = name;
		this.disk = disk;
	}

	public Computer() {}

	public void setName(String name) {
		this.name = name;
	}
	public Harddisk getDisk() {
		return disk;
	}
	public void setDisk(Harddisk disk) {
		this.disk = disk;
	}
	@Override
	public String toString() {
		return "Computer [name=" + name + ", disk=" + disk + "]";
	}
	
	
}
