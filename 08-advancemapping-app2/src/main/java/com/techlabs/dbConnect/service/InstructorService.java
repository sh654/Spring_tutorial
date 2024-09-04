package com.techlabs.dbConnect.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlabs.dbConnect.PageResponse;
import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;

public interface InstructorService {

	

	InstructorDto addInstructor(InstructorDto instructorsdto);

	Instructor allocateCourses(int instructorId, List<Course> courses);
	
	InstructorDto getInstructor(int instructorId);
	
	List<CourseDto> getInstructorCourses(int instructorId);
	
	PageResponse<InstructorDto> getAllInstructor(int pageNumber, int pageSize);
}
