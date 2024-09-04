package com.techlabs.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationDto {

	private String userName;
	private String password;
	private String  roleName;
}
