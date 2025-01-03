package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccounts {
    @Id
    private int accountNumber;
    private String accountHolderName; // Corrected field name for better readability
    private double balance;
    private String upiId;
}
