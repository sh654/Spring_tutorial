package com.techlabs.dbConnect.entity;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Client {

    @Id
    @Column(name = "clientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "registrationnumber")
    private String registrationNumber;

    @Column(name = "contactperson")
    private String contactPerson;

    @Column(name = "contactemail")
    private String contactEmail;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING) // Storing enum as a string in the database
    @Column(name = "status")
    private Status status;

    @Column(name = "creation_date", updatable = false)
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING) // Storing enum as a string in the database
    @Column(name = "kycstatus")
    private KycStatus kycStatus;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDate.now();
    }

    public enum Status {
        APPROVED,
        REJECTED
    }

    public enum KycStatus {
        APPROVED,
        REJECTED,
        PENDING,
        SUCCESS
    }
}
