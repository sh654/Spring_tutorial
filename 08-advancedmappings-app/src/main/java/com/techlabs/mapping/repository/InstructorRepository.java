package com.techlabs.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mapping.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
