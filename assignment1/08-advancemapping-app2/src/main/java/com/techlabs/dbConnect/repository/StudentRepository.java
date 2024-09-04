package com.techlabs.dbConnect.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Page<Student> findByStudentName(String studentName, Pageable pagebale);
}
