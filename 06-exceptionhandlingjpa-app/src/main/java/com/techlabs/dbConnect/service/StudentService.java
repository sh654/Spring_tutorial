package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.dbConnect.dto.PageResponse;
import com.techlabs.dbConnect.entity.Student;

public interface StudentService {

	Page<Student> getAllStudents(int pageno, int pagesize);
	void addStudent(Student student);
	
	void deleteStudent(int rollnumber);
	Optional<Student> getStudentById(int rollnumber);
	void updateStudent(int rollnumber, Student studentDetails);
	
	Page<Student> getStudentByName(String name,int pageno, int pagesize);
	PageResponse<Student> getAllStudentsResponse(int pageno, int pagesize);
}
