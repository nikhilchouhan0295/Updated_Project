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
import org.springframework.test.context.ContextConfiguration;

import com.example.entity.Sports;
import com.example.repository.SportsRepository;
import com.example.service.SportsServiceImpl;

@SpringBootTest
@ContextConfiguration(classes = SportsServiceApplication.class)
class SportsServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	

    @Mock
    private SportsRepository sportsRepository;

    @InjectMocks
    private SportsServiceImpl sportsService;

    @BeforeEach 
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSport() {
        Sports sport = new Sports(1, "Cricket", "Outdoor");
        when(sportsRepository.save(sport)).thenReturn(sport);

        Sports result = sportsService.addSport(sport);

        assertNotNull(result);
        assertEquals("Cricket", result.getSportName());
        verify(sportsRepository, times(1)).save(sport);
    }

    @Test
    void testUpdateSport_Success() {
        Sports sport = new Sports(1, "Football", "Outdoor");
        when(sportsRepository.existsById(1)).thenReturn(true);
        when(sportsRepository.save(sport)).thenReturn(sport);

        String result = sportsService.updateSport(sport);

        assertEquals("Sport Updated Successfully!!", result);
        verify(sportsRepository, times(1)).existsById(1);
        verify(sportsRepository, times(1)).save(sport);
    }

    @Test
    void testUpdateSport_Failure() {
        Sports sport = new Sports(1, "Basketball", "Indoor");
        when(sportsRepository.existsById(1)).thenReturn(false);

        String result = sportsService.updateSport(sport);

        assertEquals("Sport Not Updated,Please update Again", result);
        verify(sportsRepository, times(1)).existsById(1);
        verify(sportsRepository, never()).save(sport);
    }

    @Test
    void testDeleteSport_Success() {
        when(sportsRepository.findById(1)).thenReturn(Optional.empty());

        String result = sportsService.deleteSport(1);

        assertEquals("Sport Deleted Successfully!!", result);
        verify(sportsRepository, times(1)).deleteById(1);
        verify(sportsRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteSport_Failure() {
        Sports sport = new Sports(1, "Tennis", "Outdoor");
        when(sportsRepository.findById(1)).thenReturn(Optional.of(sport));

        String result = sportsService.deleteSport(1);

        assertEquals("Sport Not Deleted, Please try again!!", result);
        verify(sportsRepository, times(1)).deleteById(1);
        verify(sportsRepository, times(1)).findById(1);
    }

    @Test
    void testGetSportById_Success() {
        Sports sport = new Sports(1, "Hockey", "Outdoor");
        when(sportsRepository.findById(1)).thenReturn(Optional.of(sport));

        Sports result = sportsService.getSportById(1);

        assertNotNull(result);
        assertEquals("Hockey", result.getSportName());
        verify(sportsRepository, times(1)).findById(1);
    }

    @Test
    void testGetSportById_NotFound() {
        when(sportsRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> sportsService.getSportById(1));
        assertEquals("Sport Not Found!!", exception.getMessage());
        verify(sportsRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllSports() {
        Sports sport1 = new Sports(1, "Cricket", "Outdoor");
        Sports sport2 = new Sports(2, "Football", "Outdoor");
        List<Sports> sportsList = Arrays.asList(sport1, sport2);
        when(sportsRepository.findAll()).thenReturn(sportsList);

        List<Sports> result = sportsService.getAllSports();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sportsRepository, times(1)).findAll();
    }

}
