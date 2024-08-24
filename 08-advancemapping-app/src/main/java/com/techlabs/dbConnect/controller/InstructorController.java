package com.techlabs.dbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;
import com.techlabs.dbConnect.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<Instructor> addNewInstructor(@RequestBody InstructorDto instructordto){
		return ResponseEntity.ok(instructorService.addInstructor(instructordto));
	}
	
	@PutMapping("/instructors/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId,@RequestBody List<Course> courses){
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId, courses));
	}
}
