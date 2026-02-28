/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.time.LocalDateTime; // Для работы с датой

public class BankAccount {

    // --- 1. Поля класса ---
    private String ownerName;           // Имя владельца
    private int balance;                // Баланс (целое число)
    private LocalDateTime openingDate;  // Дата открытия
    private boolean isBlocked;          // Статус блокировки (true - заблокирован)

    // --- 2. Конструктор ---
    // Принимает только имя, остальное заполняет сам.
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;                       // Начальный баланс 0
        this.openingDate = LocalDateTime.now(); // Текущая дата и время
        this.isBlocked = false;                 // Счет активен (не заблокирован)
    }

    // --- 3. Методы ---

    // Метод пополнения счета (deposit)
    public boolean deposit(int amount) {
        // Проверка: сумма должна быть положительной, и счет не должен быть заблокирован
        if (amount <= 0 || isBlocked) {
            return false; // Операция не удалась
        }
        
        balance += amount; // Увеличиваем баланс (оператор +=)
        return true;       // Операция прошла успешно
    }

    // Метод снятия денег (withdraw)
    public boolean withdraw(int amount) {
        // Проверка: сумма > 0, счет не заблокирован, и ДЕНЕГ ХВАТАЕТ
        if (amount <= 0 || isBlocked || amount > balance) {
            return false; // Нельзя снять больше, чем есть
        }

        balance -= amount; // Уменьшаем баланс (оператор -=)
        return true;
    }

    // Метод перевода денег (transfer)
    public boolean transfer(BankAccount otherAccount, int amount) {
        // Проверяем условия для отправителя (себя)
        // 1. Наш счет не заблокирован
        // 2. Сумма перевода корректна
        // 3. На нашем счете достаточно денег
        if (this.isBlocked || amount <= 0 || amount > this.balance) {
            return false;
        }

        // Проверяем получателя: его счет не должен быть заблокирован
        if (otherAccount.isBlocked) {
            return false;
        }

        // Если всё ок — выполняем перевод
        this.balance -= amount;         // Снимаем у себя
        otherAccount.balance += amount; // Добавляем другому
        return true;
    }

    // Вспомогательный метод, чтобы видеть состояние счета в консоли
    public void printInfo() {
        System.out.println("Счет: " + ownerName + " | Баланс: " + balance + " | Заблокирован: " + isBlocked);
    }
}
