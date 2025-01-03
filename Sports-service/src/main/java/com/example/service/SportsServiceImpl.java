
package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Sports;
import com.example.repository.SportsRepository;



@Service
public class SportsServiceImpl implements SportsService{
	
	@Autowired
	private SportsRepository sportsRepository;

	@Override
	public Sports addSport(Sports sport) {
		return sportsRepository.save(sport);
	}

	@Override
	public String updateSport(Sports sport) {
		boolean alreadyExisted = sportsRepository.existsById(sport.getSportId());
		if(alreadyExisted) {
			sportsRepository.save(sport);
			return "Sport Updated Successfully!!"; 
	    }
		return "Sport Not Updated,Please update Again";
	}

	
	@Override
	public String deleteSport(int sportId) {
		sportsRepository.deleteById(sportId);
		Sports sport = sportsRepository.findById(sportId).orElse(null);
		if(sport==null) {
			return "Sport Deleted Successfully!!";
		}
		return "Sport Not Deleted, Please try again!!";
	}

	@Override
	public Sports getSportById(int sportId) {
		return sportsRepository.findById(sportId).orElseThrow(()-> new RuntimeException("Sport Not Found!!"));
	}

	@Override
	public List<Sports> getAllSports() {
		List<Sports> sportList = sportsRepository.findAll();
		return sportList;
	}

}
