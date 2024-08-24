package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
