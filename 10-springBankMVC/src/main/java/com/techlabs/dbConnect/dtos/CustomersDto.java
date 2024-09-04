package com.techlabs.dbConnect.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CustomersDto {

    private int customerId;
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "First name should contain only letters and spaces")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Last name should contain only letters and spaces")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
        message = "Email must be valid and follow the standard format (e.g., user@example.com)"
    )
    private String email;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;  
    
    
}

