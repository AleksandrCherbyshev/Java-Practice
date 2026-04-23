/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.rating.repository;

import com.mycompany.restaurant.rating.model.Visitor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitorRepository {
    private final List<Visitor> visitors = new ArrayList<>();
    private long idCounter = 1;

    public Visitor save(Visitor visitor) {
        if (visitor.getId() == null) {
            visitor.setId(idCounter++);
            visitors.add(visitor);
        } else {
            // Если ID уже есть, обновляем существующего (упрощенная логика)
            remove(visitor.getId());
            visitors.add(visitor);
        }
        return visitor;
    }

    public void remove(Long id) {
        visitors.removeIf(v -> v.getId().equals(id));
    }

    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }
}
