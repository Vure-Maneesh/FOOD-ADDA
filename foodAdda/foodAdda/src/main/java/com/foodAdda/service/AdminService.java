package com.foodAdda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foodAdda.entity.Restaurant;
import com.foodAdda.exception.ResourceNotFoundException;
import com.foodAdda.repository.Restaurantrepo;

@Service
public class AdminService {

    @Autowired
    private Restaurantrepo restaurantrepo;

    public List<Restaurant> getPendingRestaurants() {
        System.out.println("Inside admin service at getpending method");

        try {
            List<Restaurant> pendingRest = restaurantrepo.findByStatus("PENDING");

            if (pendingRest.size() == 0) {
                throw new Exception("No restaurant found");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return restaurantrepo.findByStatus("PENDING");

    }

    public void approveRestaurant(Long restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantrepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not found"));
        if (!"PENDING".equalsIgnoreCase(restaurant.getStatus())) {
            throw new IllegalStateException("Only pending restaurant can be approved");
        }

        restaurant.setStatus("ACCEPTED");
        restaurantrepo.save(restaurant);

    }

    public void rejectRestaurant(Long restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantrepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not found"));
        if (!"PENDING".equalsIgnoreCase(restaurant.getStatus())) {
            throw new IllegalStateException("Only pending restaurant can be rejected");
        }

        restaurant.setStatus("REJECTED");
        restaurantrepo.save(restaurant);

    }

    public List<Restaurant> getLowratedRestaurants() {
        return restaurantrepo.findByRatingLessThan(1.5);
    }

    public void removeRestaurant(Long restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantrepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not found"));

        restaurantrepo.delete(restaurant);
    }

}
