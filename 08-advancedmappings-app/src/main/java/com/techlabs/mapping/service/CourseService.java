package com.techlabs.mapping.service;

import java.util.List;

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.entity.Course;

public interface CourseService {
	
	CourseDto addCourse(CourseDto courseDto);

	List<CourseDto> getAllCourses();
	
	CourseDto getCourseById(int courseId);

	CourseDto assignInstructor(int courseId, int instructorid);

	CourseDto assignStudents(int courseId, int StudentsId);
}
