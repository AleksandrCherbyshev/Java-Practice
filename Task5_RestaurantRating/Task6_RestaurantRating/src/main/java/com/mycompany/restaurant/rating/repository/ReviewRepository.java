/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.repository;

import com.mycompany.restaurant.rating.model.Review;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();
    private long idCounter = 1;

    public Review save(Review review) {
        if (review.getId() == null) {
            review.setId(idCounter++);
            reviews.add(review);
        } else {
            remove(review.getId());
            reviews.add(review);
        }
        return review;
    }

    public void remove(Long id) {
        reviews.removeIf(r -> r.getId().equals(id));
    }

    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }

    public Review findById(Long id) {
        return reviews.stream()
            .filter(r -> r.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
