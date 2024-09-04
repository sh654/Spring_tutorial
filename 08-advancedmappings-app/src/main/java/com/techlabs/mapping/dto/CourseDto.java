package com.techlabs.mapping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
	
	private int courseId;
	
	private String name;

	private int duration;
	
	private double fees;	

}
