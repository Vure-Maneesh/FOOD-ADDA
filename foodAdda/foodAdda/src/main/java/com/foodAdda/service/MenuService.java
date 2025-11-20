package com.foodAdda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodAdda.entity.Menu;
import com.foodAdda.entity.Restaurant;
import com.foodAdda.exception.ResourceNotFoundException;
import com.foodAdda.repository.MenuRepository;
import com.foodAdda.repository.Restaurantrepo;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private Restaurantrepo restaurantrepo;

    public Menu addDish(Long restaurantId, Menu menus) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantrepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not Found!!"));

        if (!"APPROVED".equalsIgnoreCase(restaurant.getStatus())) {
            throw new IllegalStateException("Restaurant is not approved");
        }

        if (!menus.getName().matches("^[A-Z][a-zA-Z ]+$")) {
            throw new IllegalStateException("Dish name must start with uppercase and contain only alphabets/spaces");
        }

        List<String> validTypes = List.of("Vegetarian", "Non-Vegetarian", "Eggetarian", "Jain");
        if (!validTypes.contains(menus.getType())) {
            throw new IllegalStateException("Invalid dish type");
        }

        if (menus.getPrice() <= 0) {
            throw new IllegalStateException("Price must be greater than 0");
        }

        menus.setRestaurant(restaurant);
        return menuRepository.save(menus);
    }

    public Menu updateDish(Long menuId, Menu updated) throws ResourceNotFoundException {

        Menu me = menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Dish not Found"));

        if (!updated.getName().matches("^[A-Z][a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Dish name must start with uppercase and contain only alphabets/spaces");
        }

        if (!List.of("Vegetarian", "Non-vegetarian", "Eggetarian", "Jain").contains(updated.getType())) {
            throw new IllegalArgumentException("Invalid dish type");
        }

        if (updated.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        // update all fields except rating
        me.setName(updated.getName());
        me.setType(updated.getType());
        me.setDescription(updated.getDescription());
        me.setSpecialty(updated.getSpecialty());
        me.setPrice(updated.getPrice());

        return menuRepository.save(me);
    }

    // -------------------- DELETE DISH --------------------
    @SuppressWarnings("unused")
    public void deleteDish(Long menuId) throws ResourceNotFoundException {
        Optional<Menu> menu = menuRepository.findById(menuId);

        if (menu == null) {
            throw new ResourceNotFoundException("Dish not Found");
        }
        menuRepository.deleteById(menuId);
    }

    // -------------------- GET MENU LIST --------------------

    public List<Menu> getMenuByRestaurant(Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

}
