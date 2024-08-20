package com.techlabs.dbConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")   // An entity to do ORM ie object relationship mapping
public class Student {
	@Column(name="rollnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // best practice to use identity
	private int rollnumber;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;

	
	public Student(int rollnumber, String name, int age) {
		super();
		this.rollnumber = rollnumber;
		this.name = name;
		this.age = age;
	}
	
	public Student() {}

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
