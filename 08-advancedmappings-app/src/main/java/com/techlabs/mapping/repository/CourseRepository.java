package com.techlabs.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mapping.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
