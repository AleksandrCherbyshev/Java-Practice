/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Запускаем задания по очереди
        runTask1_Arrays();
        runTask2_Collections();
        runTask3_EqualsHashCode();
        runTask4_StreamAPI();
    }

    // --- ЗАДАНИЕ 1: Массивы ---
    private static void runTask1_Arrays() {
        System.out.println("\n=== ЗАДАНИЕ 1: Массивы ===");
        int[] years = new int[50];
        Random random = new Random();
        int currentYear = Year.now().getValue(); // Текущий год (2025 или 2026)
        long sumAge = 0;

        System.out.println("Машины моложе 2015 года:");
        for (int i = 0; i < years.length; i++) {
            // Генерируем год от 2000 до 2025
            years[i] = random.nextInt(26) + 2000; 

            // Сразу считаем возраст для среднего
            sumAge += (currentYear - years[i]);

            // Вывод только тех, что после 2015
            if (years[i] > 2015) {
                System.out.print(years[i] + " ");
            }
        }
        System.out.println(); // Перенос строки

        // Средний возраст
        double avgAge = (double) sumAge / years.length;
        System.out.println("Средний возраст автопарка: " + avgAge + " лет");
    }

    // --- ЗАДАНИЕ 2: Коллекции ---
    private static void runTask2_Collections() {
        System.out.println("\n=== ЗАДАНИЕ 2: Коллекции ===");
        // Создаем список с дубликатами
        List<String> models = new ArrayList<>();
        models.add("Toyota Camry");
        models.add("BMW X5");
        models.add("Tesla Model S"); // Это превратится в ELECTRO_CAR
        models.add("Tesla Model 3"); // Это тоже
        models.add("BMW X5");        // Дубликат
        models.add("Audi A4");

        // Обработка:
        // 1. Замена Tesla -> ELECTRO_CAR
        // 2. Удаление дубликатов (distinct)
        // 3. Сортировка в обратном порядке (sorted)
        // 4. Сохранение в Set
        
        Set<String> result = models.stream()
                .map(model -> model.contains("Tesla") ? "ELECTRO_CAR" : model) // Замена
                .distinct() // Удаление дубликатов (BMW X5 уйдет, два ELECTRO_CAR схлопнутся в один)
                .sorted(Comparator.reverseOrder()) // Обратный алфавитный порядок
                .peek(System.out::println) // Вывод на экран в процессе
                .collect(Collectors.toCollection(LinkedHashSet::new)); // Сохраняем в Set (Linked чтобы сохранить порядок сортировки)

        System.out.println("Итог в Set: " + result);
    }

    // --- ЗАДАНИЕ 3: Сравнение и HashSet ---
    private static void runTask3_EqualsHashCode() {
        System.out.println("\n=== ЗАДАНИЕ 3: Equals/hashCode ===");
        Set<Car> carSet = new HashSet<>();

        Car car1 = new Car("111", "Toyota", "Camry", 2020, 10000, 25000);
        Car car2 = new Car("222", "BMW", "X5", 2022, 5000, 60000);
        Car car3 = new Car("111", "Toyota", "Corolla", 2010, 200000, 5000); // VIN как у car1!

        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3); // Не должен добавиться, так как VIN "111" уже есть

        System.out.println("Размер HashSet (ожидаем 2): " + carSet.size());
        for (Car c : carSet) {
            System.out.println(c);
        }

        // Проверка сортировки (Comparable)
        System.out.println("- Сортировка списка (от новых к старым): -");
        List<Car> sortedList = new ArrayList<>(carSet);
        Collections.sort(sortedList); // Использует наш метод compareTo в классе Car
        for (Car c : sortedList) {
            System.out.println(c);
        }
    }

    // --- ЗАДАНИЕ 4: Stream API ---
    private static void runTask4_StreamAPI() {
        System.out.println("\n=== ЗАДАНИЕ 4: Stream API ===");
        List<Car> cars = Arrays.asList(
            new Car("1", "Toyota", "Camry", 2020, 30000, 25000),
            new Car("2", "BMW", "X5", 2022, 10000, 80000),
            new Car("3", "Toyota", "RAV4", 2019, 60000, 22000),
            new Car("4", "Mercedes", "S-Class", 2023, 5000, 120000),
            new Car("5", "Lada", "Vesta", 2018, 90000, 8000)
        );

        System.out.println("1. Топ-3 дорогих машин с пробегом < 50 000:");
        cars.stream()
            .filter(c -> c.getMileage() < 50000) // Фильтр по пробегу
            .sorted(Comparator.comparingDouble(Car::getPrice).reversed()) // Сортировка по цене убывание
            .limit(3) // Берем только 3
            .forEach(System.out::println);

        System.out.println("2. Средний пробег всех машин:");
        double avgMileage = cars.stream()
            .mapToInt(Car::getMileage)
            .average()
            .orElse(0);
        System.out.println((int)avgMileage + " км");

        System.out.println("3. Группировка по производителю:");
        Map<String, List<Car>> byMaker = cars.stream()
            .collect(Collectors.groupingBy(Car::getManufacturer));
        
        byMaker.forEach((maker, list) -> {
            System.out.println(maker + ": " + list.size() + " шт.");
        });
    }
}
