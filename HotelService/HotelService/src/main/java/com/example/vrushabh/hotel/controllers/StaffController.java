package com.example.vrushabh.hotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity<List<String>> getAllStaff(){
        List<String> list = Arrays.asList("ram","shyam","sita","krishna");
        return ResponseEntity.ok(list);
    }
}
