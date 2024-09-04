package com.techlabs.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.InstructorRepository;
import com.techlabs.mapping.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private InstructorRepository instructorRepo;

	@Autowired
	private StudentRepository studentRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Override
	public CourseDto addCourse(CourseDto courseDto) {
	
		Course course = toCourseMapper(courseDto);
		CourseDto dbCourse;
		course=courseRepo.save(course);
		dbCourse = toCourseDtoMapper(course);
		logger.info("Course Added Successfully"+dbCourse.getCourseId());
		
		return dbCourse;
	}
	
	private Course toCourseMapper(CourseDto courseDto)
	{
		Course course=new Course();
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		course.setName(courseDto.getName());
		return course;
	}
	
	private CourseDto toCourseDtoMapper(Course course)
	{
		CourseDto courseDto=new CourseDto();
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		courseDto.setName(course.getName());
		courseDto.setCourseId(course.getCourseId());
		return courseDto;
	}

	@Override
	public List<CourseDto> getAllCourses() {
		
		List<Course> courses=courseRepo.findAll();
		if(courses.size()==0)
		return null;
		
		List<CourseDto> courseDtos=new ArrayList<>();
		
		courses.forEach((course)->{
			courseDtos.add(toCourseDtoMapper(course));
		});
		
		return courseDtos;
	}

	@Override
	public CourseDto getCourseById(int courseId) {

        Course course=null;
        
        Optional<Course> optionalCourse=courseRepo.findById(courseId);
        if(!optionalCourse.isPresent()) {
        	logger.error("Course with id"+courseId+" Not found");
    	return null;
        }
        course=optionalCourse.get();
        return toCourseDtoMapper(course);
	}

	@Override
	public CourseDto assignInstructor(int courseId, int instructorid) {
		Course dbCourse=toCourseMapper(getCourseById(courseId));
		dbCourse.setCourseId(courseId);			
		Instructor instructor=instructorRepo.findById(instructorid)
				              .orElseThrow(()-> new NullPointerException("Instructor Not Found"));
		dbCourse.setInstructor(instructor);
		dbCourse=courseRepo.save(dbCourse);  
		return toCourseDtoMapper(dbCourse);
	}

	@Override
	public CourseDto assignStudents(int courseId, int StudentsId) {
		Course dbCourse = toCourseMapper(getCourseById(courseId));
		dbCourse.setCourseId(courseId);
		Student student = studentRepo.findById(StudentsId).orElseThrow(() ->
		new NullPointerException("No Student Exist"));
		
		dbCourse.getStudents().add(student);
		
		dbCourse = courseRepo.save(dbCourse);
		
		return toCourseDtoMapper(dbCourse);
	}
	
	

}
