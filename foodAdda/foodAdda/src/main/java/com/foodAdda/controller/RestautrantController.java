package com.foodAdda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodAdda.entity.Restaurant;
import com.foodAdda.exception.DuplicateRestaurant;
import com.foodAdda.service.RestuarantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestautrantController {

    @Autowired
    private RestuarantService restuarantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@Valid @RequestBody Restaurant restaurant) throws DuplicateRestaurant {
        Restaurant response = restuarantService.registerRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("get")
    public List<Restaurant> getall() {
        return restuarantService.getallRestuarant();
    }
}