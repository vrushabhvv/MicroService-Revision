package com.example.vrushabh.RatingService.impl;

import com.example.vrushabh.RatingService.entity.Rating;
import com.example.vrushabh.RatingService.repository.RatingRepository;
import com.example.vrushabh.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(long ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()->
            new RuntimeException("Rating with id "+ratingId+" not found")
        );
    }

    @Override
    public List<Rating> getAllRatingbyUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingbyHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
