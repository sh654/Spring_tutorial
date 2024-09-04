package com.techlabs.mapping.service;

import java.util.List;

import com.techlabs.mapping.dto.PageResponse;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Student;

public interface StudentService {
	
	StudentDto addStundent(Student student);

	Address updateStudentAddress(int rollnumber, Address address);

	PageResponse<StudentDto> getAllStudents(int pageNumber, int pageSize);
	
	StudentDto getStudentByRollNumber(int rollnumber);
	
	StudentDto assignCourses(int rollnumber, List<Integer> courseIds);
}
