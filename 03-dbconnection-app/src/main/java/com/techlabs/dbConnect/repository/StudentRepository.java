package com.techlabs.dbConnect.repository;

import java.util.List;

import com.techlabs.dbConnect.entity.Student;

public interface StudentRepository {
	// To communicate with database we use repository
	List<Student> getAllStudents();

	Student getStudent(int rollnumber);
	void addStudent(Student student);
	List<Student> getAllStudentByName(String name);
	void deleteStudent(int rollnumber);
}
