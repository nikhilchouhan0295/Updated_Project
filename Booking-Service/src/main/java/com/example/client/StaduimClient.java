package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.StadiumDto;

@FeignClient(name="STADIUM-SERVICE")
public interface StaduimClient {
	@GetMapping("/getStadiumById/{stadiumId}")
	StadiumDto  getStadiumById(@PathVariable int id);
}
