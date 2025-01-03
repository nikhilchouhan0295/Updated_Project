package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Booking;
import com.example.entity.BookingDTO;
import com.example.repository.BookingRepository;
@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking createBooking(BookingDTO bookingDTO) {
		Booking booking = new Booking();
	    booking.setBookingId(bookingDTO.getBookingId());
	    booking.setUserId(bookingDTO.getUserId());
	    booking.setSportsId(bookingDTO.getSportsId());
		booking.setSelectedSeats(bookingDTO.getSelectedSeats());
		
	    return bookingRepository.save(booking);
	}

	@Override
	public Booking getBookingById(int bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking Not Found!!"));
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking deleteBooking(int bookingId) {
		Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking Not Found!!"));
		bookingRepository.delete(booking);
		return booking;
	}

	@Override
	public List<Booking> byUserId(int userId) {
		return bookingRepository.findByUserId(userId);
	}

}
