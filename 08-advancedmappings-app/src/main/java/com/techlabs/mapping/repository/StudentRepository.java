package com.techlabs.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mapping.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
