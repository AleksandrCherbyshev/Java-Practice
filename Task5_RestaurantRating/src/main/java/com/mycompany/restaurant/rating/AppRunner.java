/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating;

import com.mycompany.restaurant.rating.model.*;
import com.mycompany.restaurant.rating.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;

    public AppRunner(VisitorService visitorService, RestaurantService restaurantService, ReviewService reviewService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }
    private final ReviewService reviewService;

    // Наполнение данными при запуске (как в задании)
    @PostConstruct
    public void init() {
        // Создаем посетителя
        Visitor v1 = visitorService.save(new Visitor(null, "Иван", 25, Gender.MALE));
        
        // Создаем ресторан
        Restaurant r1 = restaurantService.save(new Restaurant(
                null, "Пицца-Парк", "Лучшая пицца", CuisineType.ITALIAN, 
                BigDecimal.valueOf(1000), BigDecimal.ZERO
        ));

        // Оставляем отзыв (рейтинг должен пересчитаться автоматически)
        reviewService.save(new Review(null, v1.getId(), r1.getId(), 5, "Очень вкусно!"));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- ТЕСТ СИСТЕМЫ РЕЙТИНГА ---");
        
        restaurantService.findAll().forEach(r -> {
            System.out.println("Ресторан: " + r.getName());
            System.out.println("Средний чек: " + r.getAverageCheck());
            System.out.println("Рейтинг: " + r.getRating());
        });
        
        System.out.println("-----------------------------\n");
    }
}
