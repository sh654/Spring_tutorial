package com.techlabs.dbConnect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import com.techlabs.dbConnect.enums.TransactionType;

@Entity
@Table(name = "Transactions")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Transactions {

    @Id
    @Column(name="transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Accounts account;

    @Enumerated(EnumType.STRING)
    @Column(name = "transactionType", nullable=false)
    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @Column(name="transactAmount", nullable = false)
    @Min(value = 1, message = "Amount must be greater than 1")
    private double Amount;

    @Column(name="senderAccountNumber" , nullable = false)
    @NotNull(message = "Sender account number is required")
    private Long senderAccountNumber;

    @Column(name = "receiverAccountNumber")
    @NotNull(message = "Receiver account number is required")
    private Long receiverAccountNumber;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }
	 
    
}
