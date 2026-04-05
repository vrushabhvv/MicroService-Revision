package com.example.user.controller;

import com.example.user.entities.User;
import com.example.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    int retry = 0;

    //create user
    //if you hit /user with post method then this handler will execute
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    //get all user
    //if you hit /user with get method then this handler will execute
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    //get single user
    @GetMapping("/{id}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack") //commenting to implement retry module of resillience4j for this handler
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")//commenting to implement rateLimiter
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        retry++;
        log.info("retry {} ", retry);
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    //creating fallback method
    //dont forget, 1.name should be same as of provided in fallbackMethod attribute
    //             2.return type should be same as of handler method
    //             3.number of parameters should be of same type and number of parameter
    //             should be same  + Throwable or Exception type of parameter is allowed

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception exception) {
        log.info("fallback is executed because service is down:", exception.getMessage());
        User user = User.builder().
                email("dummy@gmail.com").
                name("dummy").
                about("this user is created because of some service is down").userId("1234").build();
        return ResponseEntity.ok(user);

    }
    public ResponseEntity<User> rateLimiterFallback(String userId, Exception exception) {
        log.info("fallback is executed because too many request:", exception.getMessage());
        User user = User.builder().
                email("dummy@gmail.com").
                name("dummy").
                about("this user is created because of too many request").userId("1234").build();

        return new ResponseEntity<User>(user,HttpStatus.TOO_MANY_REQUESTS);

    }


}
