package com.example.service;

import java.util.List;

import com.example.entity.Stadium;

public interface StaduimService {
	Stadium addStadium(Stadium stadium);
	String updateStadium(Stadium stadium);
	String deleteStadium(long stadiumId);
	Stadium getStadiumById(long stadiumId);
	List<Stadium> getAllStadiums();
}
 