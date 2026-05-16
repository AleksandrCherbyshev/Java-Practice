/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.dto;


public record ReviewResponseDTO(Long id, Long visitorId, Long restaurantId, int score, String text) {}
