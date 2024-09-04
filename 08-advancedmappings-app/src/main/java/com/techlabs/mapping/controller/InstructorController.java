package com.techlabs.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponse;
import com.techlabs.mapping.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<InstructorDto> addNewInstructor(@RequestBody InstructorDto instructorDto)
	{
		return ResponseEntity.ok(instructorService.addInstrcutor(instructorDto));
	}
	
	@GetMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorDto> getInstructor(@PathVariable int instructorId)
	{
		return ResponseEntity.ok(instructorService.getInstructorById(instructorId));
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<PageResponse<InstructorDto>> getAllInstructor(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		return ResponseEntity.ok(instructorService.getAllInstructors(pageNumber, pageSize));
	}
	
	@PutMapping("/instructors/courses")
	public ResponseEntity<InstructorDto> allocateCourses(@RequestParam int instructorId, @RequestBody List<Integer> courseIds)
	{
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId,courseIds));
	}

}
