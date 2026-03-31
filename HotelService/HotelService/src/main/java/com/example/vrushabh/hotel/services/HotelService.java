package com.example.vrushabh.hotel.services;

import com.example.vrushabh.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create Hotel
    Hotel createHotel(Hotel hotel);

    //get all Hotel
    List<Hotel> getAllHotel();

    //get specific hotel
    Hotel getHotelById(Long id);
}
