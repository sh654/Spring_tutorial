package com.techlabs.dbConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;
import com.techlabs.dbConnect.service.CourseService;

@RestController
@RequestMapping("/studentapp")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addNewCourse(@RequestBody CourseDto coursedto){
		return ResponseEntity.ok(courseService.addCourse(coursedto));
	}
	
	@PutMapping("/courses/instructor")
	public ResponseEntity<Course> addInstructor(@RequestParam int courseId,@RequestBody Instructor instructor){
		return ResponseEntity.ok(courseService.allocateInstructor(courseId, instructor));
	}
}
