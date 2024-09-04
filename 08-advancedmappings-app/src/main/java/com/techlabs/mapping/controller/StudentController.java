package com.techlabs.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mapping.dto.PageResponse;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/students")
	public ResponseEntity<StudentDto> addNewStudent(@RequestBody Student student)
	{
		return ResponseEntity.ok(studentService.addStundent(student));
	}
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<StudentDto>> getAllStudent(@RequestParam(name="pageNumber") int pageNumber,@RequestParam(name="pageSize") int pageSize){
		return ResponseEntity.ok(studentService.getAllStudents(pageNumber, pageSize));
	}
	
	@PutMapping("/students/address")
	public ResponseEntity<Address> addNewStudent(@RequestBody Address address, @RequestParam int rollnumber)
	{
		return ResponseEntity.ok(studentService.updateStudentAddress(rollnumber,address));
	}
	
	@PutMapping("/students/assigncourses")
	public ResponseEntity<StudentDto> assignCourse(@RequestParam(name="rollnumber") int rollnumber, @RequestBody List<Integer> courseIds){
		return ResponseEntity.ok(studentService.assignCourses(rollnumber, courseIds));
	}
	

}
