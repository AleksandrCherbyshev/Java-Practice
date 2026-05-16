/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.dto;

import com.mycompany.restaurant.rating.model.CuisineType;
import java.math.BigDecimal;

public record RestaurantRequestDTO(String name, String description, CuisineType cuisineType, BigDecimal averageCheck) {}
