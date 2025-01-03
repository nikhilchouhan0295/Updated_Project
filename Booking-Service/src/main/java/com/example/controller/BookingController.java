package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Booking;
import com.example.entity.BookingDTO;
import com.example.exception.GlobalExceptionHandler;
import com.example.service.BookingServiceImpl;

@RestController
public class BookingController {
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	
	@PostMapping("/addBooking")
	public Booking createBooking(@RequestBody BookingDTO bookingDTO) {
		return bookingServiceImpl.createBooking(bookingDTO);
	}
	@GetMapping("/byId/{bookingId}")
	public Booking getBookingById(@PathVariable int bookingId){
		return bookingServiceImpl.getBookingById(bookingId);
	}
	@DeleteMapping("/delete/{bookingId}")
	public Booking deleteBooking(@PathVariable int bookingId){
		return bookingServiceImpl.deleteBooking(bookingId);
	}
	@GetMapping("/{userId}")
	public List<Booking> byUserName(@PathVariable int userId){
		return bookingServiceImpl.byUserId(userId);
	}

} 
