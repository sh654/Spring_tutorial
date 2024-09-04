package com.techlabs.mapping.service;

import java.util.List;

import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponse;

public interface InstructorService {
	
	InstructorDto addInstrcutor(InstructorDto instructorDto);
	InstructorDto getInstructorById(int instructorId);
	PageResponse<InstructorDto> getAllInstructors(int pageNumber, int pageSize);
	InstructorDto allocateCourses(int instructorId, List<Integer> courseIds);

}
