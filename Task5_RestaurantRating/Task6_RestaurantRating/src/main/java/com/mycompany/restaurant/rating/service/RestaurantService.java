/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.dto.RestaurantRequestDTO;
import com.mycompany.restaurant.rating.dto.RestaurantResponseDTO;
import com.mycompany.restaurant.rating.model.Restaurant;
import com.mycompany.restaurant.rating.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public RestaurantResponseDTO save(RestaurantRequestDTO dto) {
        Restaurant r = new Restaurant(null, dto.name(), dto.description(), dto.cuisineType(), dto.averageCheck(), BigDecimal.ZERO);
        r = repository.save(r);
        return mapToResponse(r);
    }

    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        Restaurant r = repository.findById(id);
        if (r != null) {
            r.setName(dto.name());
            r.setDescription(dto.description());
            r.setCuisineType(dto.cuisineType());
            r.setAverageCheck(dto.averageCheck());
            repository.save(r);
            return mapToResponse(r);
        }
        return null;
    }

    public void remove(Long id) {
        repository.remove(id);
    }

    public List<RestaurantResponseDTO> findAll() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // Для API
    public RestaurantResponseDTO findById(Long id) {
        Restaurant r = repository.findById(id);
        return r != null ? mapToResponse(r) : null;
    }

    // Для внутреннего использования (пересчет рейтинга)
    public Restaurant getEntityById(Long id) {
        return repository.findById(id);
    }

    // Вспомогательный метод (используется при пересчете рейтинга)
    public void saveEntity(Restaurant restaurant) {
        repository.save(restaurant);
    }

    private RestaurantResponseDTO mapToResponse(Restaurant r) {
        return new RestaurantResponseDTO(r.getId(), r.getName(), r.getDescription(), r.getCuisineType(), r.getAverageCheck(), r.getRating());
    }
}
