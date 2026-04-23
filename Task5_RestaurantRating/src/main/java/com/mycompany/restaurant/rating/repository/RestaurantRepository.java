/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.repository;

import com.mycompany.restaurant.rating.model.Restaurant;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private long idCounter = 1;

    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(idCounter++);
            restaurants.add(restaurant);
        } else {
            remove(restaurant.getId());
            restaurants.add(restaurant);
        }
        return restaurant;
    }

    public void remove(Long id) {
        restaurants.removeIf(r -> r.getId().equals(id));
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    public Restaurant findById(Long id) {
        return restaurants.stream()
            .filter(r -> r.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
