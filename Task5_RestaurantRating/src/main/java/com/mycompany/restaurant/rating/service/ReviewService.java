/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.model.Restaurant;
import com.mycompany.restaurant.rating.model.Review;
import com.mycompany.restaurant.rating.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantService restaurantService; // Внедряем сервис ресторанов для обновления

    public Review save(Review review) {
        Review savedReview = reviewRepository.save(review);
        updateRestaurantRating(savedReview.getRestaurantId()); // Пересчитываем рейтинг!
        return savedReview;
    }

    public ReviewService(ReviewRepository reviewRepository, RestaurantService restaurantService) {
        this.reviewRepository = reviewRepository;
        this.restaurantService = restaurantService;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void remove(Long id) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            Long restaurantId = review.getRestaurantId();
            reviewRepository.remove(id);
            updateRestaurantRating(restaurantId); // Пересчитываем после удаления!
        }
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    // Логика пересчета средней оценки
    private void updateRestaurantRating(Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        if (restaurant == null) return;

        List<Review> allReviews = reviewRepository.findAll().stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .toList();

        if (allReviews.isEmpty()) {
            restaurant.setRating(BigDecimal.ZERO);
        } else {
            double average = allReviews.stream()
                    .mapToInt(Review::getScore)
                    .average()
                    .orElse(0.0);
            
            // Сохраняем среднюю оценку округленную до 1 знака
            restaurant.setRating(BigDecimal.valueOf(average).setScale(1, RoundingMode.HALF_UP));
        }
        restaurantService.save(restaurant); // Сохраняем обновленный ресторан
    }
}
