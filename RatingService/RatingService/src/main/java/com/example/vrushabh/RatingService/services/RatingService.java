package com.example.vrushabh.RatingService.services;

import com.example.vrushabh.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating createRating(Rating rating);

    // get all ratings
    List<Rating> getAllRatings();

    //get specific rating
    Rating getRatingById(long ratingId);

    //get all rating by userId
    List<Rating> getAllRatingbyUserId(String userId);

    //get all rating by hotel
    List<Rating> getAllRatingbyHotelId(String hotelId);
}
