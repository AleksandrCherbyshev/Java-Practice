/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.dto.VisitorRequestDTO;
import com.mycompany.restaurant.rating.dto.VisitorResponseDTO;
import com.mycompany.restaurant.rating.model.Visitor;
import com.mycompany.restaurant.rating.repository.VisitorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final VisitorRepository repository;

    public VisitorService(VisitorRepository repository) {
        this.repository = repository;
    }

    public VisitorResponseDTO save(VisitorRequestDTO dto) {
        Visitor visitor = new Visitor(null, dto.name(), dto.age(), dto.gender());
        visitor = repository.save(visitor);
        return mapToResponse(visitor);
    }

    public VisitorResponseDTO update(Long id, VisitorRequestDTO dto) {
        Visitor visitor = repository.findById(id);
        if (visitor != null) {
            visitor.setName(dto.name());
            visitor.setAge(dto.age());
            visitor.setGender(dto.gender());
            repository.save(visitor);
            return mapToResponse(visitor);
        }
        return null;
    }

    public void remove(Long id) {
        repository.remove(id);
    }

    public List<VisitorResponseDTO> findAll() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private VisitorResponseDTO mapToResponse(Visitor v) {
        return new VisitorResponseDTO(v.getId(), v.getName(), v.getAge(), v.getGender());
    }
}
