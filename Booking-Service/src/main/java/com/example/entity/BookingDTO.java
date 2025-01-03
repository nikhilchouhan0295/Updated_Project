package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private int bookingId;   // Changed to camel case
    private int userId;      // Changed to camel case
    private int sportsId;    // Changed to camel case
    private List<String> selectedSeats; // Changed to List<String> for seat details
}
