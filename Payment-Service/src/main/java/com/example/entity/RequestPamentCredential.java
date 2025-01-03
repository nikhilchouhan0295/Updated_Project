package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPamentCredential {
    private int accountNumber;
    private double withdrawAmount; // Corrected field name
    private String upiId;
}
