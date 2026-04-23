/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.service;

import com.mycompany.restaurant.rating.model.Visitor;
import com.mycompany.restaurant.rating.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository repository;

    public VisitorService(VisitorRepository repository) {
        this.repository = repository;
    }

    public VisitorRepository getRepository() {
        return repository;
    }

    public Visitor save(Visitor visitor) {
        return repository.save(visitor);
    }

    public void remove(Long id) {
        repository.remove(id);
    }

    public List<Visitor> findAll() {
        return repository.findAll();
    }
}
