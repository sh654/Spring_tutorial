package com.techlabs.dbConnect.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.techlabs.dbConnect.entity.Role;

import jakarta.validation.constraints.Email;
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
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
        message = "Email must be valid and follow the standard format (e.g., user@example.com)"
    )
    private String userName;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String roles; // Comma-separated roles
    
   

}
