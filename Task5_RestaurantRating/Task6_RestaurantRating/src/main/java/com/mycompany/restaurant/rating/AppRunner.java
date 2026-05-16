/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating;

import com.mycompany.restaurant.rating.dto.*;
import com.mycompany.restaurant.rating.model.*;
import com.mycompany.restaurant.rating.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    public AppRunner(VisitorService visitorService, RestaurantService restaurantService, ReviewService reviewService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void init() {
        // Создаем данные через DTO
        VisitorResponseDTO v1 = visitorService.save(new VisitorRequestDTO("Иван", 25, Gender.MALE));
        
        RestaurantResponseDTO r1 = restaurantService.save(new RestaurantRequestDTO(
                "Пицца-Парк", "Лучшая пицца", CuisineType.ITALIAN, BigDecimal.valueOf(1000)
        ));

        reviewService.save(new ReviewRequestDTO(v1.id(), r1.id(), 5, "Очень вкусно!"));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- ПРИЛОЖЕНИЕ ЗАПУЩЕНО ---");
        System.out.println("Swagger UI доступен по ссылке: http://localhost:8080/swagger-ui/index.html");
        System.out.println("---------------------------\n");
    }
}
