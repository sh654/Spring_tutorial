package com.techlabs.dbConnect.dto;

import java.util.List;

import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {

	private int totalPages;
	private long totalElements;
	private int size;
	private List<T> content;
	private boolean isLastPage;
	
	
}
