package com.techlabs.dbConnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.entity.Student;

import com.techlabs.dbConnect.service.StudentService;

@RequestMapping("/studentapp")
@RestController
public class StudentController {
	@Autowired
	private StudentService studentService; 
	
	@GetMapping("/students")      
	public ResponseEntity<List<Student>> getAllStudents(){   // request handling message 
		
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentService.getStudent(rollnumber));
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "Record added Successfully";
	}
	
	@GetMapping("/students/name")
	public ResponseEntity<List<Student>> getStudentByName(@RequestParam String name){
		return ResponseEntity.ok(studentService.getStudentsByName(name));
	}
	
	@DeleteMapping("/students/{rollnumber}")
	public ResponseEntity<String> deleteStudent(@PathVariable int rollnumber){
		studentService.deleteStudent(rollnumber);
		return ResponseEntity.ok("Record Deleted Successfully");
	}
	
}
