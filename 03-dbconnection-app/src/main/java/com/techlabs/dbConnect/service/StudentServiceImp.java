package com.techlabs.dbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.repository.StudentRepository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImp implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.addStudent(student);
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.getAllStudentByName(name);
	}

	@Override
	@Transactional
	public void deleteStudent(int rollnumber) {
		// TODO Auto-generated method stub
		studentRepo.deleteStudent(rollnumber);
	}

}
