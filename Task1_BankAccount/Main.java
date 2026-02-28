/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

public class Main {
    public static void main(String[] args) {
        
        // 1. Создаем два счета (объекты класса BankAccount)
        System.out.println("--- Создание счетов ---");
        BankAccount account1 = new BankAccount("Ivan");
        BankAccount account2 = new BankAccount("Maria");
        
        account1.printInfo();
        account2.printInfo();

        // 2. Тестируем пополнение (deposit)
        System.out.println("\n--- Пополнение счета Ivan на 1000 ---");
        boolean successDeposit = account1.deposit(1000);
        System.out.println("Результат пополнения: " + successDeposit);
        account1.printInfo();

        // 3. Тестируем снятие (withdraw)
        System.out.println("\n--- Попытка снять 1500 у Ivan (больше чем есть) ---");
        boolean successWithdraw = account1.withdraw(1500);
        System.out.println("Результат снятия: " + successWithdraw); // Должно быть false
        
        System.out.println("--- Снятие 200 у Ivan ---");
        account1.withdraw(200);
        account1.printInfo(); // Ожидаем баланс 800

        // 4. Тестируем перевод (transfer)
        System.out.println("\n--- Перевод 300 от Ivan к Maria ---");
        boolean successTransfer = account1.transfer(account2, 300);
        System.out.println("Результат перевода: " + successTransfer);
        
        // Проверяем итог
        System.out.println("\n--- Итоговое состояние ---");
        account1.printInfo(); // Ожидаем 500
        account2.printInfo(); // Ожидаем 300
    }
}
