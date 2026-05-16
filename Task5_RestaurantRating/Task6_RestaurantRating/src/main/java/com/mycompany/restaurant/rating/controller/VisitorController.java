/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.controller;

import com.mycompany.restaurant.rating.dto.VisitorRequestDTO;
import com.mycompany.restaurant.rating.dto.VisitorResponseDTO;
import com.mycompany.restaurant.rating.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Посетители", description = "Управление посетителями ресторана")
public class VisitorController {

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить всех посетителей")
    public List<VisitorResponseDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Добавить нового посетителя")
    public VisitorResponseDTO create(@RequestBody VisitorRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные посетителя")
    public VisitorResponseDTO update(@PathVariable Long id, @RequestBody VisitorRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить посетителя")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
