package com.techlabs.dbConnect.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="documents")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Document {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docId")
    private int documentId;

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") // Foreign key column in the document table
    private Customers customer;
    
    @Column(name = "is_verified")
    private boolean isVerified;
	
}
