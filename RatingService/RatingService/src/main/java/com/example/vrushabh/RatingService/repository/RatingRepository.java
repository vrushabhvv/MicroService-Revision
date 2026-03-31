package com.example.vrushabh.RatingService.repository;

import com.example.vrushabh.RatingService.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    //create custom methods

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
