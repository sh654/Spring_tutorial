package com.techlabs.dbConnect.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StudentError {

	private int statusCode;
	private String errorMessage;
	private long timeStamp;
	
}
