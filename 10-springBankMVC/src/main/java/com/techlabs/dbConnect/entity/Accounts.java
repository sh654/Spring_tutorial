package com.techlabs.dbConnect.entity;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techlabs.dbConnect.enums.AccountType;
import com.techlabs.dbConnect.enums.StatusType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Accounts {
	
	@Id
	@Column(name="accountId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@Column(name="accountNumber", nullable= false, unique=true)
	@NotNull(message = "Account number is required")
	private long accountNumber; // long

	@Enumerated(EnumType.STRING)
	@Column(name="accountType", nullable=false)
	@NotNull(message = "Account type is required")
	private AccountType accountType;
	
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name="amount")
    @Min(value = 1, message = "Amount must be positive")
    private double amount;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customers customer;
	
	 @OneToMany(mappedBy = "account", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	 private List<Transactions> transactions;
	 
	 @Column(name="status")
	 @Enumerated(EnumType.STRING)
	 private StatusType status;
	 
	 @PrePersist
	 protected void onCreate() {
			createdAt = LocalDateTime.now();
		}
		
	@PreUpdate
	protected void onUpdate() {
			updatedAt = LocalDateTime.now();
		}
	
		
	private String generateRandomAccountNumber() {
	    SecureRandom random = new SecureRandom();
	    StringBuilder accountNumberBuilder = new StringBuilder(10);
	    
	    for (int i = 0; i < 10; i++) {
	        accountNumberBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
	    }
	    
	    return accountNumberBuilder.toString();
	}

}
