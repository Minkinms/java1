package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        int[] testArray1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] testArray2 = {5, 2, 8, 6, -5, 0, 15, 134, 120, 10, 11};
        int[] testArray3 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        System.out.println(Arrays.toString(testArray1));
        sort(testArray1);
        System.out.println(Arrays.toString(testArray1));

        System.out.println(Arrays.toString(testArray2));
        sort(testArray2);
        System.out.println(Arrays.toString(testArray2));

        System.out.println(Arrays.toString(testArray3));
        sort(testArray3);
        System.out.println(Arrays.toString(testArray3));
    }
    //Метод для сортировки произвольного массива целых чисел
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    //Замена чисел местами без использования дополнительной переменной
                    //Подсмотрел в примерах тестовых заданий на собеседованиях :-)
                    a[i] = a[i] + a[j];
                    a[j] = a[i] - a[j];
                    a[i] = a[i] - a[j];
                }
            }
        }
    }
}
