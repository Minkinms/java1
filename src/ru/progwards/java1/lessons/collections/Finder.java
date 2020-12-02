package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
/*        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(3); //index 0
        numbers.add(4); //index 1
        numbers.add(2); //index 2
        numbers.add(5); //index 3
        numbers.add(2); //index 4

        Collection<Integer> numbers1 = new ArrayList<>();
        numbers1.add(1); //index 0
        numbers1.add(2); //index 1
        numbers1.add(3); //index 2
        numbers1.add(4); //index 3
        numbers1.add(5); //index 4

        Collection<String> names = new ArrayList<>();
        names.add(new String("Николай"));    //index 0
        names.add(new String("Ииигорь"));     //index 1
        names.add(new String("Ииигорь"));    //index 2
        names.add(new String("Ииигорь"));    //index 3
        names.add(new String("Ольга"));    //index 4
        names.add(new String("Надежда"));      //index 5
        names.add(new String("Надежда"));    //index 6
        names.add(new String("Надежда"));    //index 7
//        names.add(new String("Надежда"));    //index 8*/


//        findMinSumPair(numbers);
//        findLocalMax(numbers);
//        System.out.println(findSequence(numbers1));
//        System.out.println(findSimilar(names));

    }

    //Задача 12.2.1
    //Найти 2 соседних числа в коллекции сумма которых мимнимальна,
    //вернуть лколлекцию, содержащую индексы этих чисел
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        List<Integer> outCollection = new ArrayList<>();   //Вспомогательная коллекция

        if (numbers != null && numbers.size() >= 2){
            ArrayList<Integer> inCollection = new ArrayList<>(numbers);      //Вспомогательная коллекция, т.к. класс входного объекта не известен
            outCollection.add(0, inCollection.get(0));              //Ввожу два элемента, изначально get(0) и get(1),
            outCollection.add(1, inCollection.get(1));              //чтобы в дальнейшем их перезаписывать
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
    //Поиск локальных максимумов.
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        List<Integer> outCollection = new ArrayList<>();   //Вспомогательная коллекция
        if (numbers != null && numbers.size() >= 3) {
            ArrayList<Integer> inCollection = new ArrayList<>(numbers);      //Вспомогательная коллекция, т.к. класс входного объекта не известен
            for (int i = 1; i < inCollection.size() - 1; i++){
                if (inCollection.get(i) > inCollection.get(i-1) && inCollection.get(i) > inCollection.get(i+1)){
                    outCollection.add(inCollection.get(i));
                }
            }
        }
        return outCollection;
    }

    //Задача 12.2.3
    public static boolean findSequence(Collection<Integer> numbers){
        if (numbers != null){
            ArrayList<Integer> inCollection = new ArrayList<>(numbers);      //Вспомогательная коллекция, т.к. класс входного объекта не известен
            for (int i = 1; i <= inCollection.size(); i++){
                if (!inCollection.contains(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //Задача 12.2.4
    public static String findSimilar(Collection<String> names){
        int count = 0;                      //счетчик совпадений
        int max = 0;                        //максимум совпадений
        String outLine = "";                //Строка возвращаемого ответа

        if (names != null){
            ArrayList<String> inCollection = new ArrayList<>(names);    //Вспомогательная коллекция, т.к. класс входного объекта не известен
            for (String name1:inCollection){            //Цикл проверки
                for (String name2:inCollection){
                    if (name1.equals(name2)){
                        count++;
                    }else{
                        if (count > max){
                            max = count;
                            outLine = name1 + ":" + count;
                        }
                        count = 0;
                    }
                }
            }
        }

        return outLine;
    }

}
