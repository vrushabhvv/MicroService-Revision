package com.example.vrushabh.hotel.controllers;

import com.example.vrushabh.hotel.entity.Hotel;
import com.example.vrushabh.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //create hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    //get hotel using id
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
        Hotel hotel = hotelService.getHotelById(Long.parseLong(id));
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    //get all hotel
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotels =  hotelService.getAllHotel();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

}
