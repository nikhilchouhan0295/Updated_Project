package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Booking;
import com.example.entity.BookingDTO;

@Service
public interface BookingService {
	 Booking createBooking(BookingDTO bookingDTO);
	 Booking getBookingById(int bookingId);
	 List<Booking> getAllBookings();
	 Booking deleteBooking(int bookingId);
	 List<Booking> byUserId(int userId);
	 
}
