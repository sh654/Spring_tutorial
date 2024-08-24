package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;

public interface InstructorService {

	Instructor addInstructor(InstructorDto instructorsdto);

	Instructor allocateCourses(int instructorId, List<Course> courses);
	
}
