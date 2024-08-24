package com.techlabs.dbConnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.PageResponse;
import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.service.StudentService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(required = false) String name, @RequestParam int pageno, @RequestParam int pagesize){
		if(name !=null) {
			return ResponseEntity.ok(studentService.getStudentByName(name, pageno, pagesize));
		}
		return ResponseEntity.ok(studentService.getAllStudents(pageno, pagesize));
	}
	
	@GetMapping("/students")
    public PageResponse<Student> getAllStudents(
            @RequestParam int pageno,
            @RequestParam int pagesize) {
        return studentService.getAllStudentsResponse(pageno, pagesize);
    }
	
	@GetMapping("/student/{rollnumber}")
	public ResponseEntity<Optional<Student>> getMethodName(@PathVariable int rollnumber) {
		return ResponseEntity.ok(studentService.getStudentById(rollnumber));
	}
	
	
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student){
		studentService.addStudent(student);
		return "Added";
	}
	

	@PutMapping("/student/{rollnumber}")
    public ResponseEntity<String> updateStudent(@PathVariable int rollnumber, @RequestBody Student studentDetails) {
        try {
            studentService.updateStudent(rollnumber, studentDetails);
            return ResponseEntity.ok("Updated Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
	
	@DeleteMapping("/student/{rollnumber}")
	public ResponseEntity<String> deleteStudent(@PathVariable int rollnumber){
		studentService.deleteStudent(rollnumber);
		return ResponseEntity.ok("Deleted ");
	}
}
