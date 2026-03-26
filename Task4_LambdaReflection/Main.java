/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.function.Predicate;

// ==========================================
// ЧАСТЬ 2: АННОТАЦИИ (Описываем до Main)
// ==========================================

// 2.1 Создаем свою аннотацию @DeprecatedEx
// @Retention(RetentionPolicy.RUNTIME) означает, что аннотация будет доступна во время выполнения программы (для рефлексии)
// @Target указывает, что её можно вешать только на классы (TYPE) и методы (METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface DeprecatedEx {
    String message(); // Параметр для поясняющего сообщения
}

// Тестовый класс, на котором мы проверим нашу аннотацию
@DeprecatedEx(message = "Этот класс устарел, используйте NewUserClass")
class OldUserClass {
    
    @DeprecatedEx(message = "Метод небезопасен, используйте loginNew()")
    public void login() {
        System.out.println("Старый логин");
    }

    // На этом методе аннотации нет, рефлексия его проигнорирует
    public void logout() {
        System.out.println("Выход");
    }
}

// Обработчик аннотаций (Рефлексия)
class AnnotationHandler {
    // Метод принимает ЛЮБОЙ класс (Class<?>)
    public static void checkDeprecated(Class<?> clazz) {
        
        // 1. Проверяем сам класс
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx ann = clazz.getAnnotation(DeprecatedEx.class);
            System.out.println("! класс '" + clazz.getSimpleName() + "' устарел – альтернатива: '" + ann.message() + "'");
        }
        
        // 2. Проверяем все методы внутри класса
        // getDeclaredMethods() достает все методы, которые мы написали в классе
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx ann = method.getAnnotation(DeprecatedEx.class);
                System.out.println("! метод '" + method.getName() + "' устарел – альтернатива: '" + ann.message() + "'");
            }
        }
    }
}

// ==========================================
// ЧАСТЬ 1: ЛЯМБДЫ (Интерфейс)
// ==========================================

// 1.1 Функциональный интерфейс Printable (один метод)
@FunctionalInterface
interface Printable {
    void print();
}

// ==========================================
// ГЛАВНЫЙ КЛАСС (Точка входа)
// ==========================================
public class Main {
    public static void main(String[] args) {
        
        System.out.println("=== ЗАДАНИЕ 1.1: Printable ===");
        // Написание лямбда-выражения для Printable
        Printable printable = () -> System.out.println("Печать через лямбда-выражение: Привет, Java!");
        printable.print();

        System.out.println("\n=== ЗАДАНИЕ 1.2: Проверка строки (Predicate) ===");
        // Лямбда: не null
        Predicate<String> isNotNull = s -> s != null;
        // Лямбда: не пуста
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        // Метод and(): не null и не пуста
        Predicate<String> isFull = isNotNull.and(isNotEmpty);

        String test1 = "Hello";
        String test2 = "";
        String test3 = null;

        System.out.println("Строка '" + test1 + "' валидна? " + isFull.test(test1));
        System.out.println("Строка пустая валидна? " + isFull.test(test2));
        System.out.println("Строка null валидна? " + isFull.test(test3));

        System.out.println("\n=== ЗАДАНИЕ 1.3: Проверка J/N и A ===");
        // Начинается на J или N и заканчивается на A
        Predicate<String> jnaPredicate = s -> 
            (s.startsWith("J") || s.startsWith("N")) && s.endsWith("A");

        System.out.println("JAVA подходит? " + jnaPredicate.test("JAVA"));
        System.out.println("NETA подходит? " + jnaPredicate.test("NETA"));
        System.out.println("PYTHON подходит? " + jnaPredicate.test("PYTHON"));

        System.out.println("\n=== ЗАДАНИЕ 2: Аннотации и Рефлексия ===");
        // Вызываем наш обработчик для класса OldUserClass
        AnnotationHandler.checkDeprecated(OldUserClass.class);
    }
}
