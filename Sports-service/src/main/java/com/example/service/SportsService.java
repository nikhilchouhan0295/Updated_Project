package com.example.service;

import java.util.List;

import com.example.entity.Sports;

public interface SportsService {
	Sports addSport(Sports sport);
	String updateSport(Sports sport);
	String deleteSport(int sportId);
	Sports getSportById(int sportId);
	List<Sports> getAllSports();
}
