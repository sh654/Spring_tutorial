package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
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
		return studentRepo.findById(rollnumber);
	}
	@Override
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByName(name);
	}

	
}
