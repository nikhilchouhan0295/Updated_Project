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

import com.example.entity.Stadium;
import com.example.service.StadiumServiceImple;

@RestController
public class StadiumController {

    @Autowired
    private StadiumServiceImple stadiumServiceImple; 

    @GetMapping("/testStadium")
    public String test() {
        return "Chal rha h Stadium Controller!!";
    }

    @PostMapping("/addStadium")
    public ResponseEntity<?> addStadium(@RequestBody Stadium stadium) {
        Stadium addedStadium = stadiumServiceImple.addStadium(stadium);
        return new ResponseEntity<>(addedStadium, HttpStatus.CREATED);
    }
 
    @PutMapping("/updateStadium")
    public ResponseEntity<?> updateStadium(@RequestBody Stadium stadium) {
        String msg = stadiumServiceImple.updateStadium(stadium);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStadium/{stadiumId}")
    public ResponseEntity<?> deleteStadium(@PathVariable int stadiumId) { 
        String msg = stadiumServiceImple.deleteStadium(stadiumId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/getStadiumById/{stadiumId}")
    public ResponseEntity<?> getStadiumById(@PathVariable int stadiumId) {
        Stadium stadium = stadiumServiceImple.getStadiumById(stadiumId);
        return new ResponseEntity<>(stadium, HttpStatus.OK);
    }

    @GetMapping("/getAllStadiums")
    public ResponseEntity<List<Stadium>> getAllStadiums() {
        List<Stadium> stadiumList = stadiumServiceImple.getAllStadiums();
        return new ResponseEntity<>(stadiumList, HttpStatus.OK);
    }
}
