package com.example.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.UserDto;

@FeignClient(name="USER-SERVICE")
public interface UserClient {
	@GetMapping("/getUserDetailsbyUserId/{userId}")
	UserDto getUserDetailsbyUserId(@PathVariable int userId);
}
 