package com.techlabs.mapping.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mapping.dto.PageResponse;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepo;
	
//	@Override
//	public Student addStundent(Student student) {
//		
//		return studentRepo.save(student);
//	}

	@Override
	public Address updateStudentAddress(int rollnumber, Address address) {
		Student dbStudent=null;
		
		Optional<Student> optionalStudent=studentRepo.findById(rollnumber);
		if(!optionalStudent.isPresent())
			return null;
		dbStudent=optionalStudent.get();
		Address dbAddress=dbStudent.getAddress();
		dbAddress.setCity(address.getCity());
		dbStudent.setAddress(dbAddress);
		
		//dbStudent.setAddress(address);
		return studentRepo.save(dbStudent).getAddress();
		
	}
	
	private StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollnumber(student.getRollnumber());
		
		return studentDto;
	}
	
	private Student toStudentMapper(StudentDto studentDto) {
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		student.setRollnumber(studentDto.getRollnumber());
		
		return student;
	}

	@Override
	public StudentDto addStundent(Student student) {
		// TODO Auto-generated method stub
		
		student = studentRepo.save(student);
		return toStudentDtoMapper(student);
	}

	@Override
	public PageResponse<StudentDto> getAllStudents(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<StudentDto> response = new PageResponse<>();
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		response.setSize(studentPage.getSize());
		response.setLastPage(studentPage.isLast());
		List<StudentDto> studentDtoList = new ArrayList<>();
		studentPage.getContent().forEach((student) -> {
			studentDtoList.add(toStudentDtoMapper(student));
		});
		response.setContents(studentDtoList);
		
		return response;
	}

	@Override
	public StudentDto getStudentByRollNumber(int rollnumber) {
		// TODO Auto-generated method stub
		Student student = studentRepo.findById(rollnumber).orElseThrow(()-> 
		new NullPointerException("Student not found"));
		return toStudentDtoMapper(student);
	}

	@Override
	public StudentDto assignCourses(int rollnumber, List<Integer> courseIds) {
		// TODO Auto-generated method stub
		Student dbStudent = toStudentMapper(getStudentByRollNumber(rollnumber));
		dbStudent.setRollnumber(rollnumber);
		
		Set<Course> existingCourses = dbStudent.getCourses();
		if(existingCourses==null)
			existingCourses = new HashSet<>();
		
		Set<Course> CoursesToAdd = new HashSet<>();
		
		courseIds.forEach((ids) ->{
			Course course = courseRepo.findById(ids).orElseThrow(()-> new NullPointerException());
			
			List<Student> existingStudents = course.getStudents();
			if(existingStudents == null)
				existingStudents = new ArrayList<>();
			
			existingStudents.add(dbStudent);
			CoursesToAdd.add(course);
		});
		existingCourses.addAll(CoursesToAdd);
		dbStudent.setCourses(existingCourses);
		return toStudentDtoMapper(studentRepo.save(dbStudent));
	}

}
