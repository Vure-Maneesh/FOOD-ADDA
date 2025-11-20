package com.foodAdda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodAdda.entity.Restaurant;
import com.foodAdda.exception.ResourceNotFoundException;
import com.foodAdda.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("restaurants/pending")
    public ResponseEntity<List<Restaurant>> getPendingRestaurants() {
        // System.out.println("Before going to service pending");
        adminService.getPendingRestaurants();
        // System.out.println("After throwing the exception");
        return new ResponseEntity<List<Restaurant>>(adminService.getPendingRestaurants(), HttpStatus.OK);

    }

    @PutMapping("/restaurants/{id}/approve")
    public ResponseEntity<String> approveRestaurant(@PathVariable Long id) throws ResourceNotFoundException {
        adminService.approveRestaurant(id);

        return new ResponseEntity<>("Restaurant registration approved", HttpStatus.OK);

    }

    @PutMapping("/restaurants/{id}/reject")
    public ResponseEntity<String> rejectRestaurant(@PathVariable Long id) throws ResourceNotFoundException {
        adminService.rejectRestaurant(id);

        return new ResponseEntity<>("Restaurant registration Rejected", HttpStatus.OK);

    }

    @GetMapping("/restaurants/low-rating")
    public ResponseEntity<?> getLowerratingandRemove(@PathVariable Long id) {
        adminService.getLowratedRestaurants();
        return new ResponseEntity<>("Restaurants removed successfully", HttpStatus.OK);
    }

}
