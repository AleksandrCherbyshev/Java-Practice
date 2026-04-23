/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.model.Restaurant;
import com.mycompany.restaurant.rating.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public RestaurantRepository getRepository() {
        return repository;
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void remove(Long id) {
        repository.remove(id);
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant findById(Long id) {
        return repository.findById(id);
    }
}
