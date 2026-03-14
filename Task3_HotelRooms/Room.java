/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.Random;

// 1. Базовый абстрактный класс (abstract запрещает создание объектов этого типа)
public abstract class Room {
    private int roomNumber;
    private int maxCapacity;
    private int pricePerNight;
    private boolean isBooked;

    // 2. Конструктор
    public Room(int roomNumber, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isBooked = false; // При создании комната всегда свободна
        
        // Генерируем случайную вместимость от 1 до 4 человек
        Random random = new Random();
        this.maxCapacity = random.nextInt(4) + 1; 
    }

    // Геттеры и Сеттеры для доступа к приватным полям
    public int getRoomNumber() { return roomNumber; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " №" + roomNumber + " (Вместимость: " + maxCapacity + " чел, Цена: " + pricePerNight + ")";
    }
}

// 3. Иерархия наследования (в каждом только вызов super)

class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}

// 4. ProRoom тоже абстрактный, его нельзя создать
abstract class ProRoom extends Room {
    public ProRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}

class StandardRoom extends ProRoom {
    public StandardRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}

class LuxRoom extends ProRoom {
    public LuxRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}

class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}
