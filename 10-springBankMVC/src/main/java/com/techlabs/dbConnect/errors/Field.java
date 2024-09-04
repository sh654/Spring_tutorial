package com.techlabs.dbConnect.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Field {

	private String fieldName;
	private String fieldMessage;
	
}
