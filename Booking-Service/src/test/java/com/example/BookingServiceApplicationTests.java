package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.entity.Booking;
import com.example.entity.BookingDTO;
import com.example.repository.BookingRepository;
import com.example.service.BookingServiceImpl;

public class BookingServiceApplicationTests {

    @InjectMocks
    private BookingServiceImpl bookingService;  // Service to be tested

    @Mock
    private BookingRepository bookingRepository;  // Mocked repository

    private Booking booking;
    private BookingDTO bookingDTO;
    private List<String> selectedSeats;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Sample List of selected seats
        selectedSeats = Arrays.asList("A1", "A2", "A3");

        // Sample BookingDTO
        bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(1);
        bookingDTO.setUserId(100);
        bookingDTO.setSportsId(200);
        bookingDTO.setSelectedSeats(selectedSeats);

        // Sample Booking entity
        booking = new Booking();
        booking.setBookingId(1);
        booking.setUserId(100);
        booking.setSportsId(200);
        booking.setSelectedSeats(selectedSeats);
    }

    @Test
    public void testCreateBooking() {
        // Arrange: Mock the repository's save method
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Act: Call the service method
        Booking createdBooking = bookingService.createBooking(bookingDTO);

        // Assert: Verify the result
        assertNotNull(createdBooking);
        assertEquals(1, createdBooking.getBookingId());
        assertEquals(100, createdBooking.getUserId());
        assertEquals(selectedSeats, createdBooking.getSelectedSeats());
        verify(bookingRepository, times(1)).save(any(Booking.class));  // Verify save was called once
    }

    @Test
    public void testGetBookingById() {
        // Arrange: Mock the repository to return a booking
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));

        // Act: Call the service method
        Booking foundBooking = bookingService.getBookingById(1);

        // Assert: Verify the result
        assertNotNull(foundBooking);
        assertEquals(1, foundBooking.getBookingId());
        assertEquals(100, foundBooking.getUserId());
        assertEquals(selectedSeats, foundBooking.getSelectedSeats());
    }

    @Test
    public void testGetBookingByIdNotFound() {
        // Arrange: Mock the repository to return an empty Optional (Booking not found)
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert: Assert that the exception is thrown
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            bookingService.getBookingById(1);
        });
        assertEquals("Booking Not Found!!", thrown.getMessage());  // Check exception message
    }

    @Test
    public void testGetAllBookings() {
        // Arrange: Mock the repository to return a list of bookings
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));

        // Act: Call the service method
        List<Booking> allBookings = bookingService.getAllBookings();

        // Assert: Verify the result
        assertNotNull(allBookings);
        assertEquals(1, allBookings.size());
        assertEquals(100, allBookings.get(0).getUserId());
        assertEquals(selectedSeats, allBookings.get(0).getSelectedSeats());
    }

    @Test
    public void testDeleteBooking() {
        // Arrange: Mock the repository to return a booking when searched
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).delete(booking);  // Simulate successful delete

        // Act: Call the service method
        Booking deletedBooking = bookingService.deleteBooking(1);

        // Assert: Verify the result
        assertNotNull(deletedBooking);
        assertEquals(1, deletedBooking.getBookingId());
        verify(bookingRepository, times(1)).delete(booking);  // Verify delete was called once
    }

    @Test
    public void testDeleteBookingNotFound() {
        // Arrange: Mock the repository to return an empty Optional (Booking not found)
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert: Assert that the exception is thrown
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            bookingService.deleteBooking(1);
        });
        assertEquals("Booking Not Found!!", thrown.getMessage());  // Check exception message
    }

    @Test
    public void testGetBookingsByUserId() {
        // Arrange: Mock the repository to return bookings for a specific user
        when(bookingRepository.findByUserId(100)).thenReturn(Arrays.asList(booking));

        // Act: Call the service method
        List<Booking> bookingsByUser = bookingService.byUserId(100);

        // Assert: Verify the result
        assertNotNull(bookingsByUser);
        assertEquals(1, bookingsByUser.size());
        assertEquals(100, bookingsByUser.get(0).getUserId());
        assertEquals(selectedSeats, bookingsByUser.get(0).getSelectedSeats());
    }
}
