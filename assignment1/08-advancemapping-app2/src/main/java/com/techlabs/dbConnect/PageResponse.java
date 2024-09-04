package com.techlabs.dbConnect;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

	private int totalPages;
	private long totalElements;
	private int size;
//	private List<T> StudentPageResponse;
	private List<T> contents;
	private boolean isLastPage;
//	public void setContent(List<InstructorDto> instructorDtos);
		// TODO Auto-generated method stub
}
