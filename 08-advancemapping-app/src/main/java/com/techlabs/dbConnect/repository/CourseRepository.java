package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
