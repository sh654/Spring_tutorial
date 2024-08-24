package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.dbConnect.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();
	void addStudent(Student student);
	
	void deleteStudent(int rollnumber);
	Optional<Student> getStudentById(int rollnumber);
	void updateStudent(int rollnumber, Student studentDetails);
	
	List<Student> getStudentByName(String name);
}
