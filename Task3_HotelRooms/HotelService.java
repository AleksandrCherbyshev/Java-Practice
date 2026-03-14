/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

// 8. Создаем кастомную НЕПРОВЕРЯЕМУЮ ошибку
class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}

// 5. Интерфейс с дженериком. <T extends Room> означает "Только классы-наследники Room"
interface RoomService<T extends Room> {
    void clean(T room);
    void reserve(T room);
    void free(T room);
}

// 6 & 7. Класс-реализация интерфейса
public class HotelService implements RoomService<Room> {

    @Override
    public void clean(Room room) {
        System.out.println("🧹 Идет уборка в номере " + room.getRoomNumber() + "...");
    }

    @Override
    public void reserve(Room room) {
        // Если комната уже занята - ВЫБРАСЫВАЕМ ОШИБКУ
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException("❌ ОШИБКА: Комната №" + room.getRoomNumber() + " уже забронирована!");
        }
        
        // Иначе бронируем
        room.setBooked(true);
        System.out.println("✅ Комната №" + room.getRoomNumber() + " успешно ЗАБРОНИРОВАНА.");
    }

    @Override
    public void free(Room room) {
        room.setBooked(false);
        System.out.println("🔓 Комната №" + room.getRoomNumber() + " ОСВОБОЖДЕНА.");
    }
}
