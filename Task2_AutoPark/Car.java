/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.Objects;

// Implements Comparable<Car> нужен для сортировки (Задание 3)
public class Car implements Comparable<Car> {
    
    // Поля класса (Задание 3)
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;    // Пробег
    private double price;   // Цена

    // Конструктор
    public Car(String vin, String manufacturer, String model, int year, int mileage, double price) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    // Геттеры (нужны чтобы доставать значения полей)
    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }

    // ЗАДАНИЕ 3: Переопределение equals и hashCode
    // Две машины считаются одинаковыми, ТОЛЬКО если у них совпадает VIN.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin); // Сравниваем только VIN
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin); // Хеш-код только от VIN
    }

    // ЗАДАНИЕ 3: Сортировка (Comparable)
    // Сортируем от новых к старым (по убыванию года)
    @Override
    public int compareTo(Car other) {
        // Если this.year = 2020, other.year = 2022 -> вернет положительное (2022 - 2020 = 2), значит other "меньше" (будет раньше)
        return other.year - this.year; 
    }

    // Метод для красивого вывода в консоль
    @Override
    public String toString() {
        return "Car{" + manufacturer + " " + model + ", " + year + ", " + mileage + "км, $" + price + ", VIN='" + vin + "'}";
    }
}
