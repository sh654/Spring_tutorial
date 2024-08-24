package com.techlabs.dbConnect.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {

	private int courseId;
	

	private String courseName;
	

	private int duration;
	
	
	private double fees;
	
}
