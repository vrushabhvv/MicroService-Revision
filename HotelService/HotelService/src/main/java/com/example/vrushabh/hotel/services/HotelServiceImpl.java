package com.example.vrushabh.hotel.services;

import com.example.vrushabh.hotel.entity.Hotel;
import com.example.vrushabh.hotel.exceptions.ResourceNotFoundException;
import com.example.vrushabh.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel createdHotel = hotelRepository.save(hotel);
        return createdHotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).
                orElseThrow(() ->
                        new ResourceNotFoundException("hotel not found for given id " + id));
    }
}
