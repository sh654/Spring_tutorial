package com.techlabs.entity;

public class Student {

	private int studentId;
	private float marks;
	private String name;
	
	
	
	public Student(int studentId, float marks, String name) {
		super();
		this.studentId = studentId;
		this.marks = marks;
		this.name = name;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public float getMarks() {
		return marks;
	}


	public void setMarks(float marks) {
		this.marks = marks;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", marks=" + marks + ", name=" + name + "]";
	}
	
	
	
}
