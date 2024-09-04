package com.techlabs.dbConnect.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.techlabs.dbConnect.entity.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UsersDto {

    private int userId;
    @NotBlank(message = "Username is required")
    @Pattern(
        regexp = "^[a-zA-Z][a-zA-Z0-9_@!#$%^&*]*$",
        message = "Username must start with a letter and can contain letters, numbers, and special characters (_@!#$%^&*)"
    )
    private String userName;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String roles; // Comma-separated roles
    
   

}
