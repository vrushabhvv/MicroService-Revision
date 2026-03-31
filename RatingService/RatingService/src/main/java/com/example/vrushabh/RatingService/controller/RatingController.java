package com.example.vrushabh.RatingService.controller;

import com.example.vrushabh.RatingService.entity.Rating;
import com.example.vrushabh.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.ok().body(ratingService.createRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getRatingById(Long.parseLong(id)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getAllRatingbyUserId(id));
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getAllRatingbyHotelId(id));
    }

    //what is use of ResponseEntity
    //to create custom http response we use this

    //will ResponseEntity take care of serialization
//    Yes, ResponseEntity will handle the serialization automatically.
//
//    When you return ResponseEntity.ok(...), Spring Boot uses a library called Jackson (which is included by default)
//    to convert your Java List<Rating> into a JSON array before sending it to the browser or Postman.
//
//    How the Serialization Process Works:
//    The Trigger: Spring sees that you are returning a ResponseEntity.
//    The Lookup: It looks at the "Accept" header of the incoming request (usually application/json)
//    The Conversion: Jackson inspects your Rating class, takes all the private fields (like id, rating, feedback),
//    and converts them into JSON keys.
//    The Response: It wraps that JSON in an HTTP 200 OK status and sends it out.

}
