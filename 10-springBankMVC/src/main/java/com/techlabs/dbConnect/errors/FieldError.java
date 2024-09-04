package com.techlabs.dbConnect.errors;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class FieldError {

	private int statusCode;
	private List<Field> message;
	private Date timestamp;
	
}
