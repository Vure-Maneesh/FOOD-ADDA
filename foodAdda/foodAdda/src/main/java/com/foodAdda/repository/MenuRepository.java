package com.foodAdda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodAdda.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByNameAndRestaurantId(String name, Long restaurantId);

    List<Menu> findByRestaurantId(Long restaurantId);

}