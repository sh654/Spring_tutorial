package com.techlabs.dbConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="students")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Student {

	@Column(name="rollnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollnumber;
	
	@Column(name="age")
	@Min(18)
	private int age;
	
	@Column(name="name")
	@NotBlank(message= "Name is mandatory")
	private String name;

	
/*
	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	*/
	
}
