package com.techlabs.dbConnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
	public Course getCourse(Integer courseId) {
		return courseRepo.findById(courseId).orElseThrow();
		
	}

	@Override
	public CourseDto addCourses(CourseDto courseDto) {
		// TODO Auto-generated method stub
		Course course = toCourseMapper(courseDto);
		course = courseRepo.save(course);
		return toCourseDtoMapper(course);
	}
	
	private Course toCourseMapper(CourseDto courseDto) {
		Course course = new Course();
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		course.setCourseName(courseDto.getCourseName());
		
		return course;
	}
	
	private CourseDto toCourseDtoMapper(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setDuration(course.getDuration());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setFees(course.getFees());
		courseDto.setCourseId(course.getCourseId());
		return null;
	}

	@Override
	public CourseDto assignInstructor(int courseId, int instructorId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Course allocateInstructor(int courseId, Instructor instructor) {
		// TODO Auto-generated method stub
		Optional<Course> courseOptional = courseRepo.findById(courseId);
		if(!courseOptional.isPresent()) {
			return null;
		}
		Course courseDb = courseOptional.get();
		
		Instructor instructorDb;
		Optional<Instructor> instructorOptional = instructorRepo.findById(instructor.getInstructorId());
		if(!instructorOptional.isPresent()) { return null; }
		
		instructorDb = instructorOptional.get();
		
		courseDb.setInstructor(instructorDb);
		
		return courseRepo.save(courseDb);
	}

	/*
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

	@Override
	public List<CourseDto> getInstructorCourses(int instructorId) {
		// TODO Auto-generated method stub
		List<Course> courseDb = courseRepo.findAllByInstructor(instructorService.getInstructor(instructorId));
		
		List<CourseDto> courses = new ArrayList<>();
		
		courseDb.forEach((course) -> {
			courses.add(toCourseDto(course));
		});
 		return courses;
	}
	
	private CourseDto toCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		
		return courseDto;
	}
	*/
	
}
