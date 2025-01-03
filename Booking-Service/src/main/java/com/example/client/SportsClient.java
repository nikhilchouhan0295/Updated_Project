package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.SportsDto;

//import com.example.entity.Sports;


@FeignClient(name="SPORTS-SERVICE")
public interface SportsClient {
	
	@GetMapping("getSportById/{sportId}")
	SportsDto getSportById(@PathVariable int sportId);

}
   