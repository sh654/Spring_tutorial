package com.techlabs.dbConnect.exception;

public class StudentNotFoundException extends RuntimeException{

	public String getMessage() {
		return "Student You are searching is not Present";
	}
}
