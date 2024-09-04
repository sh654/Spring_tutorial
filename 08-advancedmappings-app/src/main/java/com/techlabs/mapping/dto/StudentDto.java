package com.techlabs.mapping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StudentDto {

	private int rollnumber;
	
	private String name;

	private int age;
	
	
}
