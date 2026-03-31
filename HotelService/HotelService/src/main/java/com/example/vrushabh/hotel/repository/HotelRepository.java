package com.example.vrushabh.hotel.repository;

import com.example.vrushabh.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
