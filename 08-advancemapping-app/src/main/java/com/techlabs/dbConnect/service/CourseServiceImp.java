package com.techlabs.dbConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;
import com.techlabs.dbConnect.repository.CourseRepository;
import com.techlabs.dbConnect.repository.InstructorRepository;

@Service
public class CourseServiceImp implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;

	@Override
	public Course addCourse(CourseDto coursedto) {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setCourseName(coursedto.getCourseName());
		course.setDuration(coursedto.getDuration());
		course.setFees(coursedto.getFees());
		return courseRepo.save(course);
	}

	@Override
	public Course allocateInstructor(int courseId, Instructor instructor) {
		// TODO Auto-generated method stub
		Optional<Course> courseOptional = courseRepo.findById(courseId);
		if(!courseOptional.isPresent())
			return null;
		
		Course dbCourse = courseOptional.get();
		
		Instructor dbInstructor;
		Optional<Instructor> instructoroptional = instructorRepo.findById(instructor.getInstructorId());
		if(!instructoroptional.isPresent())
			return null;
		
		dbInstructor = instructoroptional.get();
		
		dbCourse.setInstructor(dbInstructor);
		
		return courseRepo.save(dbCourse);
	}
	
	
}
