package com.techlabs.dbConnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;
import com.techlabs.dbConnect.repository.CourseRepository;
import com.techlabs.dbConnect.repository.InstructorRepository;

@Service
public class InstructorServiceImp implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
//	@Override
//	public Instructor addInstructor(Instructor instructor) {
//		// TODO Auto-generated method stub
//		return 
//	}

	@Override
	public Instructor addInstructor(InstructorDto instructorsdto) {
		// TODO Auto-generated method stub
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorsdto.getInstructorName());
		instructor.setInstructorEmail(instructorsdto.getInstructorEmail());
		instructor.setQualifications(instructorsdto.getQualifications());
		
		return instructorRepo.save(instructor); // since we cant directly add dto as repo is mapped to  instructor
	}

	@Override
	public Instructor allocateCourses(int instructorId, List<Course> courses) {
		// TODO Auto-generated method stub
		Instructor dbInstructor;
		Optional<Instructor> instructorOptional = instructorRepo.findById(instructorId);
		if(!instructorOptional.isPresent())
			return null;
		dbInstructor = instructorOptional.get();
		
		List<Course> dbCourses = dbInstructor.getCourses();
		
		//List<Course> toUpdateCourses = new ArrayList<>();
		
		courses.forEach((course)->{
			Course temp =courseRepo.findById(course.getCourseId()).get();
			
			temp.setInstructor(dbInstructor);
			dbCourses.add(temp);
		});
		
		dbInstructor.setCourses(dbCourses);
		
		return instructorRepo.save(dbInstructor);
	}

	// course instructor allocate
	
}
