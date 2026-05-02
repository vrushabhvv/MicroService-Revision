package com.example.user.impl;

import com.example.user.entities.Hotel.Hotel;
import com.example.user.entities.Rating;
import com.example.user.entities.User;
import com.example.user.exceptions.ResourceNotFoundException;
import com.example.user.external.services.HotellService;
import com.example.user.repositories.UserRepository;
import com.example.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotellService hotelService;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate random user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //getting single user
    @Override
    public User getUserById(String userId) {
        //get user based on id
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found for given userId " + userId)
        );
        //we need to fetch what are the rating provided from this user,establish
        // connection between USERSERVICE and RATINGSERVE

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/rating/user/dd3daa71-d590-4518-a942-0f2b73893362".trim(),
                Rating[].class);
        logger.info("ratings {}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        //we need to find this rating of which hotel, because we have hotel id in Rating service.
        //first add Hotel type instance variable inside Rating class
        List<Rating> ratingWithHotel = ratings.stream().map(rating -> {
            //call Hotel Service Api
//            ResponseEntity<Hotel> hotelEntityWithRating = restTemplate.getForEntity("http://HOTELSERVICE/hotel/" + rating.getHotelId(), Hotel.class);
//            logger.info("status of this call is {}", hotelEntityWithRating.getStatusCode());
//            rating.setHotel(hotelEntityWithRating.getBody());

//            ResponseEntity<Hotel> hotelEntityWithRating, remember <Hotel> this type is of body.

            //we will call hotelservice with feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingWithHotel);
        return user;
    }
}
