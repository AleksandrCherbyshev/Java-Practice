/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

public class Main {
    public static void main(String[] args) {
        // Создаем наш сервис для работы с комнатами
        HotelService service = new HotelService();

        // 9. Создаем разные экземпляры комнат (только конкретные классы!)
        // Room r = new Room(...); // ОШИБКА, т.к. абстрактный
        // ProRoom p = new ProRoom(...); // ОШИБКА, т.к. абстрактный
        
        Room economy = new EconomyRoom(101, 1500);
        Room standard = new StandardRoom(202, 3500);
        Room ultraLux = new UltraLuxRoom(505, 12000);

        System.out.println("=== Начало работы отеля ===");
        System.out.println(economy);
        System.out.println(standard);
        System.out.println(ultraLux);
        System.out.println();

        // Тестируем базовые методы
        service.clean(economy);
        service.reserve(economy);
        
        service.reserve(standard);
        service.free(standard);

        // ТЕСТИРУЕМ ИСКЛЮЧЕНИЕ (Пункт 8)
        System.out.println("\n--- Проверка бронирования уже занятой комнаты ---");
        try {
            service.reserve(ultraLux); // Первая бронь - успешно
            System.out.println("Пробуем забронировать повторно...");
            service.reserve(ultraLux); // Тут должна вылететь наша ошибка
        } catch (RoomAlreadyBookedException e) {
            // Перехватываем наше кастомное исключение и выводим сообщение
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }

        System.out.println("\n=== Итоговый статус комнат ===");
        System.out.println("Номер 101 забронирован: " + economy.isBooked());
        System.out.println("Номер 505 забронирован: " + ultraLux.isBooked());
    }
}
