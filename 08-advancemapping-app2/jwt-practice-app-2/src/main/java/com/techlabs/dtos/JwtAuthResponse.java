package com.techlabs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class JwtAuthResponse {

	private String accessToken;
	private String tokenType="Bearer";
	
}
