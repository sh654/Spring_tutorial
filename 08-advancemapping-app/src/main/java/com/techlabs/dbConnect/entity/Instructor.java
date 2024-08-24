package com.techlabs.dbConnect.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="instructors")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Instructor {
	
	@Id
	@Column(name="instructorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	@Column(name="instructorName")
	private String instructorName;
	@Column(name="instructorEmail")
	private String instructorEmail;
	@Column(name="instructorQualifications")
	private String qualifications;
	
	//CascadeType.PERSIST,
	@OneToMany(mappedBy = "instructor", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // mapping instructor to know which instructor has how many course
	@JsonIgnore
	private List<Course> courses; // can be set instead of list to avoid duplication of course
}
