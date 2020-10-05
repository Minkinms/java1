package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    public static void main(String[] args) {
        Eratosthenes e = new Eratosthenes(16);
        //System.out.println(Arrays.toString(e.sieve));
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
         //System.out.println(Arrays.toString(sieve));
         sift();
    }

    //Метод для сортировки чисел
    private void sift() {
        int p = 2;      //Имя переменной и начальное значение из условия задачи
        boolean exitWhile = false;      //Вспомогательная переменная

        //Внешний цикл проверки чисел-индексов массива sieve
        while (p <= sieve.length - 1) {
            for (int j = 2; j < sieve.length - 1; j++) {        //Цикл для перебора множителей 2р, 3p и т.д.
                if ((p * j) <= (sieve.length - 1)) {            //Условие и функция для вычеркивания чисел
                    sieve[p * j] = false;
                }
            }
            //System.out.println(Arrays.toString(sieve));
            for (int i = p + 1; i <= sieve.length - 1; i++) {   //Цикл для поиска следующего незачеркнутого
                if (sieve[i] == true) {     //Если число-индекс Истина, то значение р обновляется,
                                            //цикл прерывается и перехожу к вычеркиванию след. группы чисел
                    p = i;
                    break;
                } else {
                    if (i == sieve.length - 1){   //Если все числа-индексы проверены, цикл while пора заканчивать
                        exitWhile = true;
                    }
                }
            }
            //Условие для выхода из бесконечного цикла
            //Если все числа-индексы проверены и true до конца больше нет, то выходим из цикла
            if (exitWhile == true){
                break;
            }
        }

    }

    //Метод для возвращения "Простое-составное"
    public boolean isSimple(int n) {
        if (n <= sieve.length - 1){
            return sieve[n];
        } else {
            System.out.println("Число " + n + " выходит за границы повренного ряда");
            return false;
        }
    }
}
