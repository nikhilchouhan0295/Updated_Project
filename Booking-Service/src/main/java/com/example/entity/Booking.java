package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId; // Changed to camel case
    private int sportsId;  // Changed to camel case
    private int userId;    // Changed to camel case
    private List<String> selectedSeats; // Changed to List<String> for seat details
    private double totalPrice;

    // If you intend to use relationships, uncomment and configure as needed:
    // @ManyToOne
    // private Sports sports;
}
