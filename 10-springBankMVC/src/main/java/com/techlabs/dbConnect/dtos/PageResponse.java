package com.techlabs.dbConnect.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PageResponse<T> {
	
	private int totalPages;
	private int size;
	private Long totalElements;
	private boolean lastPage;
	private List<T> contents;

}
