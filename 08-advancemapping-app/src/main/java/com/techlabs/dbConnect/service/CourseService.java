package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;

public interface CourseService {

	Course addCourse(CourseDto coursedto);
	
	Course allocateInstructor(int courseId, Instructor instructor);
}
