package com.foodAdda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodAdda.entity.Address;
import com.foodAdda.entity.Restaurant;
import com.foodAdda.entity.User;
import com.foodAdda.exception.DuplicateRestaurant;
import com.foodAdda.repository.AddressRepository;
import com.foodAdda.repository.Restaurantrepo;
import com.foodAdda.repository.UserRepository;

@Service
public class RestuarantService {

    @Autowired
    private Restaurantrepo restaurantrepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository adressRepository;

    public Restaurant registerRestaurant(Restaurant restaurant) throws DuplicateRestaurant {

        User vendor = userRepository.findById(restaurant.getVendor().getId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (!vendor.getUserRole().equalsIgnoreCase("vendor")) {
            throw new DuplicateRestaurant("You are not a vendor");
        }

        restaurant.setVendor(vendor);

        Optional<Restaurant> data = restaurantrepo.findByNameAndContactNo(restaurant.getName(),
                restaurant.getContactNo());

        if (data.isPresent()) {
            throw new DuplicateRestaurant("Sorry ! Restaurant already exists for this user and contact Number");
        }

        Address saved = adressRepository.save(restaurant.getAddress());

        restaurant.setAddress(saved);
        // restaurant.setStatus("PENDING");
        // restaurant.setRating(2.5);

        restaurantrepo.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getallRestuarant() {
        return restaurantrepo.findAll();
    }
}