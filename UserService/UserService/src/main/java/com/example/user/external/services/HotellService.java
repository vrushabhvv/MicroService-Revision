package com.example.user.external.services;

import com.example.user.entities.Hotel.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotellService {
    @GetMapping("/hotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
