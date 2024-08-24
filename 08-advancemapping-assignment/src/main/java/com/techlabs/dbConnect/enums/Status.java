package com.techlabs.dbConnect.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Status {
	
	Received,
	Pending,
	Declined,
	Approved
	
}
