package com.techlabs.dbConnect.errors;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserError {

	private int statusCode;
	private String message;
	private Date timestamp;
	
}
