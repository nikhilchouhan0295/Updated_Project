package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.Stadium;
import com.example.repository.StaduimRepository;
import com.example.service.StadiumServiceImple;

@SpringBootTest
class StadiumServiceApplicationTests {
	@Mock
    private StaduimRepository staduimRepository;

    @InjectMocks
    private StadiumServiceImple stadiumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStadium() {
        Stadium stadium = new Stadium(1L, "Narendra Modi Stadium", "Ahmedabad, Gujarat", 132000);
        when(staduimRepository.save(stadium)).thenReturn(stadium);

        Stadium result = stadiumService.addStadium(stadium);

        assertNotNull(result);
        assertEquals("Narendra Modi Stadium", result.getName());
        verify(staduimRepository, times(1)).save(stadium);
    }

    @Test
    void testUpdateStadium_Success() {
        Stadium stadium = new Stadium(1L, "Eden Gardens", "Kolkata, West Bengal", 68000);
        when(staduimRepository.existsById(1L)).thenReturn(true);
        when(staduimRepository.save(stadium)).thenReturn(stadium);

        String result = stadiumService.updateStadium(stadium);

        assertEquals("Stadium updated successfully", result);
        verify(staduimRepository, times(1)).existsById(1L);
        verify(staduimRepository, times(1)).save(stadium);
    }

    @Test
    void testUpdateStadium_Failure() {
        Stadium stadium = new Stadium(1L, "Wankhede Stadium", "Mumbai, Maharashtra", 33000);
        when(staduimRepository.existsById(1L)).thenReturn(false);

        String result = stadiumService.updateStadium(stadium);

        assertEquals("Stadium Not Updated,Please update Again", result);
        verify(staduimRepository, times(1)).existsById(1L);
        verify(staduimRepository, never()).save(stadium);
    }

    @Test
    void testDeleteStadium_Success() {
        when(staduimRepository.findById(1L)).thenReturn(Optional.empty());

        String result = stadiumService.deleteStadium(1L);

        assertEquals("Stadium Deleted Successfully!!", result);
        verify(staduimRepository, times(1)).deleteById(1L);
        verify(staduimRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteStadium_Failure() {
        Stadium stadium = new Stadium(1L, "M. A. Chidambaram Stadium", "Chennai, Tamil Nadu", 50000);
        when(staduimRepository.findById(1L)).thenReturn(Optional.of(stadium));

        String result = stadiumService.deleteStadium(1L);

        assertEquals("Stadium Not Deleted, Please try again!!", result);
        verify(staduimRepository, times(1)).deleteById(1L);
        verify(staduimRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStadiumById_Success() {
        Stadium stadium = new Stadium(1L, "M. Chinnaswamy Stadium", "Bengaluru, Karnataka", 40000);
        when(staduimRepository.findById(1L)).thenReturn(Optional.of(stadium));

        Stadium result = stadiumService.getStadiumById(1L);

        assertNotNull(result);
        assertEquals("M. Chinnaswamy Stadium", result.getName());
        verify(staduimRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStadiumById_NotFound() {
        when(staduimRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> stadiumService.getStadiumById(1L));
        assertEquals("Stadium Not Found!!", exception.getMessage());
        verify(staduimRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllStadiums() {
        Stadium stadium1 = new Stadium(1L, "Greenfield International Stadium", "Thiruvananthapuram, Kerala", 55000);
        Stadium stadium2 = new Stadium(2L, "Rajiv Gandhi International Cricket Stadium", "Hyderabad, Telangana", 40000);
        List<Stadium> stadiumList = Arrays.asList(stadium1, stadium2);
        when(staduimRepository.findAll()).thenReturn(stadiumList);

        List<Stadium> result = stadiumService.getAllStadiums();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(staduimRepository, times(1)).findAll();
    }
	@Test
	void contextLoads() {
	}
	

}
