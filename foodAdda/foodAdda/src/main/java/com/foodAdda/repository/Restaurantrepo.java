package com.foodAdda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodAdda.entity.Restaurant;

public interface Restaurantrepo extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameAndContactNo(String name, String contactNumber);

    List<Restaurant> findByStatus(String status);

    List<Restaurant> findByRatingLessThan(double rating);
}
