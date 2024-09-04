package com.techlabs.dbConnect.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Users {

	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="userName")
	@NotBlank(message = "Username is required")
	@Pattern(
	        regexp = "^[a-zA-Z][a-zA-Z0-9_@!#$%^&*]*$",
	        message = "Username must start with a letter and can contain letters, numbers, and special characters (_@!#$%^&*)"
	    )
	private String userName;
	
	@Column(name="password")
	@NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name="roleId"))
	private List<Role> roles;
	
	@Column(name="created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customers customer;

//	@OneToOne(cascade = CascadeType.ALL)
//	private Admin admin;
	
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
}
