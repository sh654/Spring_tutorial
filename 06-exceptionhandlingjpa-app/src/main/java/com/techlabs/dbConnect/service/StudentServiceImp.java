package com.techlabs.dbConnect.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.PageResponse;
import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.exception.StudentNotFoundException;
import com.techlabs.dbConnect.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	public PageResponse<Student> getAllStudentsResponse(int pageno, int pagesize) {
		Pageable pageable = PageRequest.of(pageno, pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		return studentPageResponse;
	}
	
	@Override
	public Page<Student> getAllStudents(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		Pageable pageable =  PageRequest.of(pageno, pagesize);
		
		return studentRepo.findAll(pageable);
	}
	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
	}
	@Override
	public void updateStudent(int rollnumber, Student studentDetails) {
        Optional<Student> existingStudent = studentRepo.findById(rollnumber);
        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setName(studentDetails.getName());
            studentToUpdate.setAge(studentDetails.getAge());
            // Set other fields as necessary

            studentRepo.save(studentToUpdate);
        } else {
            throw new RuntimeException("Student with rollnumber " + rollnumber + " not found.");
        }
    }
	@Override
	public void deleteStudent(int rollnumber) {
		// TODO Auto-generated method stub
		studentRepo.deleteById(rollnumber);
	}
	@Override
	public Optional<Student> getStudentById(int rollnumber) {
		// TODO Auto-generated method stub
		Optional<Student> dbStudent = studentRepo.findById(rollnumber);
		if(!dbStudent.isPresent()) {
			throw new StudentNotFoundException();
		}
			
		return Optional.ofNullable(dbStudent.get()); 
	}
	@Override
	public Page<Student> getStudentByName(String name, int pageno, int pagesize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageno, pagesize);
		return studentRepo.findByName(name, pageable);
	}

	
}
