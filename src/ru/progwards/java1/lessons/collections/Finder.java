package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(3); //index 0
        numbers.add(1); //index 1
        numbers.add(2); //index 2
        numbers.add(3); //index 3
        numbers.add(2); //index 4

        findMinSumPair(numbers);
    }

    //Задача 12.2.1
    //Найти 2 соседних числа в коллекции сумма которых мимнимальна,
    //вернуть лколлекцию, содержащую индексы этих чисел
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        List<Integer> outCollection = new ArrayList<>();   //Вспомогательная коллекция

        if (numbers != null && numbers.size() >= 2){
            ArrayList<Integer> inCollection = new ArrayList<>(numbers);      //Вспомогательная коллекция, т.к. класс входного объекта не известен
            outCollection.add(0, 0);              //Ввожу два элемента, изначально нули,
            outCollection.add(1, 0);              //чтобы в дальнейшем их перезаписывать
            int minSumm = inCollection.get(0) + inCollection.get(1);    //Исходное значение минимальной суммы
            for (int i = 1; i < inCollection.size() - 1; i++){          //Цикл перебора сумм
                int num1 = inCollection.get(i) + inCollection.get(i+1);
                if (num1 < minSumm){
                    outCollection.set(0, i);
                    outCollection.set(1, i+1);
                    minSumm = num1;
                }
            }
        }
        return outCollection;
    }

    //Задача 12.2.2
    //
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        return numbers;
    }

    //Задача 12.2.3
    public static boolean findSequence(Collection<Integer> numbers){
        return false;
    }

    //Задача 12.2.4
    public static String findSimilar(Collection<String> names){
        return new String(" ");
    }

}
