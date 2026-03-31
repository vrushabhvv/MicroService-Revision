package com.example.user.services;

import com.example.user.entities.User;

import java.util.List;

public interface UserService {
    //user related operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUsers();

    //get single based  on Id
    User getUserById(String userId);
}
