package com.techlabs.dbConnect.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techlabs.dbConnect.enums.KycStatus;
import com.techlabs.dbConnect.enums.StatusType;

import ch.qos.logback.core.status.StatusBase;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customers {

    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id")
    private Users user;

    @Column(name = "firstName")
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "First name should contain only letters and spaces")
    private String firstName;

    @Column(name = "lastName", length = 50)
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Last name should contain only letters and spaces")
    private String lastName;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
        message = "Email must be valid and follow the standard format (e.g., user@example.com)"
    )
    private String email;

    @Column(name = "phone", unique = true, length = 10)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "address", length = 255)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Accounts> accounts;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Document> documents;
    
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusType status;
    
    @Column(name="KycStatus")
    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
