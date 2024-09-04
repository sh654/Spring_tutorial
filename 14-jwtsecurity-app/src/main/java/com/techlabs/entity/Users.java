package com.techlabs.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@AllArgsConstructor
@Data
@Entity
@Table(name="users")
public class Users {
	
	@Id
	@Column(name="usersId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usersid;
	
	@Column(name= "userName")
	private String userName;
	
	@Column(name="userPassword")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER) // 1 directional many to many 
	@JoinTable(joinColumns = @JoinColumn(name="usersID"), inverseJoinColumns = @JoinColumn(name="roleId"))
	private List<Role> roles;
}
