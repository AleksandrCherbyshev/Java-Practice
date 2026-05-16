/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.controller;

import com.mycompany.restaurant.rating.dto.RestaurantRequestDTO;
import com.mycompany.restaurant.rating.dto.RestaurantResponseDTO;
import com.mycompany.restaurant.rating.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Рестораны", description = "Управление ресторанами")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить все рестораны")
    public List<RestaurantResponseDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ресторан по ID")
    public RestaurantResponseDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать ресторан")
    public RestaurantResponseDTO create(@RequestBody RestaurantRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить ресторан")
    public RestaurantResponseDTO update(@PathVariable Long id, @RequestBody RestaurantRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить ресторан")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
