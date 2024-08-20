package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();
	
	Student getStudent(int rollnumber);
	
	void addStudent(Student student);
	
	List<Student> getStudentsByName(String name);
	void deleteStudent(int rollnumber);
}
