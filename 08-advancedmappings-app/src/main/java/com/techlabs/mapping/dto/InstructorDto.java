package com.techlabs.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorDto {
	
	private int instructorId;
	
	private String name;
	
	private String email;
	
	private String qualification;

}
