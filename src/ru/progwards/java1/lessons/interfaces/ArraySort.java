package ru.progwards.java1.lessons.interfaces;

public class ArraySort {
    public static void main(String[] args) {
        CompareWeight[] array = new Animal[3];
        array[0] = new Animal(3.0);
        array[1] = new Animal(1.0);
        array[2] = new Animal(2.0);
        System.out.println(array[0] + " : " + array[1] + " : " + array[2]);
        sort(array);
        System.out.println(array[0] + " : " + array[1] + " : " + array[2]);
    }

    //сортировка
    public static void sort(CompareWeight[] a){
        CompareWeight.CompareResult result; //вспомогательная переменная для наглядности

        //Вспомогательный массив для хранения промежуточного значения
        CompareWeight[] helpArray = new CompareWeight[1];

        //Цикл сортировки из ArraySort
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                //Отличие от ArraySort. Для сравнения элементов массива используем метод интерфейса,
                //который точно будет переопределен и результат его будет из вариантов CompareResult
                result =  a[i].compareWeight(a[j]);
                if (result == CompareWeight.CompareResult.GREATER) {  // реализация "if (a[i] > a[j])"
                    helpArray[0] = a[i];
                    a[i] = a[j];
                    a[j] = helpArray[0];

                }
            }
        }



    }
}
