/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.controller;

import com.mycompany.restaurant.rating.dto.ReviewRequestDTO;
import com.mycompany.restaurant.rating.dto.ReviewResponseDTO;
import com.mycompany.restaurant.rating.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Отзывы", description = "Управление отзывами и оценками")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить все отзывы")
    public List<ReviewResponseDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Добавить отзыв (авто-пересчет рейтинга)")
    public ReviewResponseDTO create(@RequestBody ReviewRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить отзыв")
    public ReviewResponseDTO update(@PathVariable Long id, @RequestBody ReviewRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отзыв")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
