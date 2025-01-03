
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Sports;
import com.example.service.SportsService;


@RestController
public class SportsController {
	
	@Autowired
	private SportsService sportsService;
	
	@GetMapping("/test")
	public String test() {
		return "Chal rha h sport Controller!!";
	}
	
	@PostMapping("/addSport")
	public ResponseEntity<?> addSport(@RequestBody Sports sport){
		Sports addedSport = sportsService.addSport(sport);
		return new ResponseEntity<>(addedSport, HttpStatus.CREATED);
	}
	 
	@PutMapping("/updateSport")
	public ResponseEntity<?> updateSport(@RequestBody Sports sport){
		String msg = sportsService.updateSport(sport);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSport/{sportId}")
	public ResponseEntity<?> deleteSport(@PathVariable int sportId){
		String msg = sportsService.deleteSport(sportId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/getSportById/{sportId}")
	public ResponseEntity<?> getSportById(@PathVariable int sportId){
		Sports sport = sportsService.getSportById(sportId);
		return new ResponseEntity<>(sport, HttpStatus.OK);
	}
	
	@GetMapping("/getAllSports")
	public ResponseEntity<List<Sports>> getAllSports(){
		List<Sports> sportList = sportsService.getAllSports();
		return new ResponseEntity<>(sportList, HttpStatus.OK);
	}
}
