package com.techlabs.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.service.CourseService;

@RestController
@RequestMapping("/studentapp")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<CourseDto> addNewCourse(@RequestBody CourseDto courseDto)
	{
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDto>> addAllCourses()
	{
		return ResponseEntity.ok(courseService.getAllCourses());
	}
	
	@PostMapping("/courses/instructor")
	public ResponseEntity<CourseDto> assignInstructor(@RequestParam(name="courseId") int courseId, @RequestParam(name="instructorid") int instructorid)
	{
		return ResponseEntity.ok(courseService.assignInstructor(courseId,instructorid));
	}

}
