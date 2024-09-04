package com.techlabs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmailDetails {

	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
	
}
