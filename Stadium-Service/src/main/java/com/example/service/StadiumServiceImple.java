package com.example.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Stadium;
import com.example.repository.StaduimRepository;
@Service
public class StadiumServiceImple implements StaduimService {
	@Autowired
	private StaduimRepository staduimRepository;

	@Override
	public Stadium addStadium(Stadium stadium) {
		// TODO Auto-generated method stub
		return staduimRepository.save(stadium);
	}

	@Override
	public String updateStadium(Stadium stadium) {
		// TODO Auto-generated method stub
		boolean alreadyExisted = staduimRepository.existsById(stadium.getId());
		if(alreadyExisted) {
			staduimRepository.save(stadium);
			return "Stadium updated successfully";
		}
		return "Stadium Not Updated,Please update Again";
	}

	@Override
	public String deleteStadium(long stadiumId) {
		// TODO Auto-generated method stub
		staduimRepository.deleteById(stadiumId);
		Stadium stadium = staduimRepository.findById(stadiumId).orElse(null);
		if(stadium==null) {
			return "Stadium Deleted Successfully!!";
		}
		return "Stadium Not Deleted, Please try again!!";
	}
	@Override
	public Stadium getStadiumById(long stadiumId) {
		// TODO Auto-generated method stub
		return staduimRepository.findById(stadiumId).orElseThrow(()-> new RuntimeException("Stadium Not Found!!"));
	}

	@Override
	public List<Stadium> getAllStadiums() {
		// TODO Auto-generated method stub
		List<Stadium> stadList = new ArrayList<>();
		return stadList;
	}

	
}
