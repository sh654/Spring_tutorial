package com.techlabs.dbConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	List<Course> findAllByInstructor(InstructorDto instructorDto);
}
