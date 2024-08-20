package com.techlabs.dbConnect.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbConnect.repository.StudentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub\
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		return query.getResultList();
	}
	
	@Override
	public Student getStudent(int rollnumber) {
		return manager.find(Student.class, rollnumber);
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		manager.persist(student);
	}

	@Override
	public List<Student> getAllStudentByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Student> query = manager.createQuery("select s from Student s where s.name=:theName", null);
		query.setParameter("theName", name);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteStudent(int rollnumber) {
		// TODO Auto-generated method stub
		Student student = manager.find(Student.class, rollnumber);
		if(student != null) {
			manager.remove(student);
		}
	}
}
