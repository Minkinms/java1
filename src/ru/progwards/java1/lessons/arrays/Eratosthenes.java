package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    public static void main(String[] args) {
        Eratosthenes e = new Eratosthenes(15);
        System.out.println("Число 0 :" + e.isSimple(0));
        System.out.println("Число 1 :" + e.isSimple(1));
        System.out.println("Число 2 :" + e.isSimple(2));
        System.out.println("Число 3 :" + e.isSimple(3));
        System.out.println("Число 4 :" + e.isSimple(4));
        System.out.println("Число 5 :" + e.isSimple(5));
        System.out.println("Число 6 :" + e.isSimple(6));
        System.out.println("Число 7 :" + e.isSimple(7));
        System.out.println("Число 8 :" + e.isSimple(8));
        System.out.println("Число 15 :" + e.isSimple(15));
        System.out.println("Число 16 :" + e.isSimple(16));


    }

    //Свойства класса
    private boolean[] sieve;        //Массив для хранеия значений простое или сложное

    //Конструктор класса
    //Вводим число, до которго проверяем
    public Eratosthenes(int N) {
        //Размер массива задаем по числу N
         sieve = new boolean[N];
         Arrays.fill(sieve, true);
         sift();
    }
    //Метод для сортировки чисел
    //Числа будут не проверяться на Простое, а исключаться методом перебора
    private void sift() {
        for (int i=2; i < sieve.length - 1; i++) {
            for (int j = 2; j < sieve.length -1; j++) {
                if ((i * j) <= (sieve.length)) {
                    sieve[i * j - 1] = false;
                }
            }
        }
        System.out.println(Arrays.toString(sieve));
    }

    //Метод для возвращения "Простое-составное"
    public boolean isSimple(int n) {
        if (n > 0 && n <= sieve.length) {      //Исключаю запрос на значение числа из непроверенного ряда
               return sieve[n-1];
        } else {
            System.out.println("Число " + n + " выходит за границы повренного ряда");
            return false;
        }
    }
}
