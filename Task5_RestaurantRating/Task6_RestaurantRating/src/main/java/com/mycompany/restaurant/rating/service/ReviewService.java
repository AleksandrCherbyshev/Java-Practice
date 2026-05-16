/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.dto.ReviewRequestDTO;
import com.mycompany.restaurant.rating.dto.ReviewResponseDTO;
import com.mycompany.restaurant.rating.model.Restaurant;
import com.mycompany.restaurant.rating.model.Review;
import com.mycompany.restaurant.rating.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantService restaurantService;

    public ReviewService(ReviewRepository reviewRepository, RestaurantService restaurantService) {
        this.reviewRepository = reviewRepository;
        this.restaurantService = restaurantService;
    }

    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = new Review(null, dto.visitorId(), dto.restaurantId(), dto.score(), dto.text());
        review = reviewRepository.save(review);
        updateRestaurantRating(review.getRestaurantId());
        return mapToResponse(review);
    }

    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            review.setScore(dto.score());
            review.setText(dto.text());
            reviewRepository.save(review);
            updateRestaurantRating(review.getRestaurantId());
            return mapToResponse(review);
        }
        return null;
    }

    public void remove(Long id) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            Long restaurantId = review.getRestaurantId();
            reviewRepository.remove(id);
            updateRestaurantRating(restaurantId);
        }
    }

    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private void updateRestaurantRating(Long restaurantId) {
        Restaurant restaurant = restaurantService.getEntityById(restaurantId);
        if (restaurant == null) return;

        List<Review> allReviews = reviewRepository.findAll().stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .toList();

        if (allReviews.isEmpty()) {
            restaurant.setRating(BigDecimal.ZERO);
        } else {
            double average = allReviews.stream().mapToInt(Review::getScore).average().orElse(0.0);
            restaurant.setRating(BigDecimal.valueOf(average).setScale(1, RoundingMode.HALF_UP));
        }
        restaurantService.saveEntity(restaurant);
    }

    private ReviewResponseDTO mapToResponse(Review r) {
        return new ReviewResponseDTO(r.getId(), r.getVisitorId(), r.getRestaurantId(), r.getScore(), r.getText());
    }
}
