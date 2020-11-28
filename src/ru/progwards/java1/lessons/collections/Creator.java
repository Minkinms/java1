package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Creator {
    public static void main(String[] args) {
        //Creator test = new Creator();
//        fillEven(10);
//        fillOdd(10);
        fill3(5);
    }

    //Метод для заполнения коллекции четными числами
    public static Collection<Integer> fillEven(int n){
        Collection<Integer> evenNumberCollection = new ArrayList<>();
        int i = 2;
        while (evenNumberCollection.size() <= n - 1) {
            if (i % 2 == 0) {
                evenNumberCollection.add(i);
            }
            i++;
        }
        return evenNumberCollection;
    }

//    Задание: Создать коллекцию и заполнить последовательностью нечетных убывающих чисел,
//    минимальное число в коллекции 1, количество элементов в коллекции n
    //Метод для заполнения коллекции нечетными числами
    public static Collection<Integer> fillOdd(int n){
        Collection<Integer> oddNumberCollection = new ArrayList<>();
        int i = n * 2;
        while (oddNumberCollection.size() <= n - 1) {
            if (i % 2 != 0){
                oddNumberCollection.add(i);
            }
            i--;
        }


        return oddNumberCollection;
    }

/*  Задание:  создать коллекцию и заполнить ее тройками чисел.
    Каждая тройка создается по алгоритму:
    первое число тройки - индекс числа в коллекции,
    второе - тот же индекс в квадрате,
    третье - тот же индекс в кубе,
    количество элементов в коллекции n*3*/
    public static Collection<Integer> fill3(int n){
        List<Integer> one3Collection = new ArrayList<>();   //Вспомогательная коллекция
        List<Integer> fill3Collection = new ArrayList<>();

        for (int i = 0; i <= n * 3; i += 3){
            one3Collection.add(i);
            one3Collection.add(i*i);
            one3Collection.add(i*i*i);

            fill3Collection.addAll(one3Collection);
            one3Collection.clear();
        }

        return fill3Collection;
    }

}
