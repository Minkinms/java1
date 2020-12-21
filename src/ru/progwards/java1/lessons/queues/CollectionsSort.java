package ru.progwards.java1.lessons.queues;

import java.util.*;

//import static java.lang.Math.abs;

public class CollectionsSort {
    public static void main(String[] args) {
//        Collection<Integer> data1 = new ArrayList<>();
        Collection<Integer> numbers = new ArrayDeque<>();
        numbers.add(3); //index 0
        numbers.add(4); //index 1
        numbers.add(2); //index 2
        numbers.add(5); //index 3
        numbers.add(1); //index 4

        System.out.println("До сортировки, numbers: " + numbers);
        mySort(numbers);
        minSort(numbers);
        collSort(numbers);
        System.out.println(compareSort());



    }

//    Сравнение методов сортировки коллекций
//1.1 Реализовать метод "__" - переделать алгоритм из класса ArraySort из ДЗ про массивы, на коллекции.
// Не использовать встроенные методы сортировок
/*public static void sort(int[] a) {
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
}*/
    public static void mySort(Collection<Integer> data){
//        System.out.println("До сортировки: " + data);
        List<Integer> resColl = new ArrayList<>(data);
        data.clear();
        for (int i = 0; i < resColl.size(); i++) {
            for (int j = i + 1; j < resColl.size(); j++) {
                if (resColl.get(i) > resColl.get(j)) {
                    Collections.swap(resColl, i, j);//Замена чисел местами
                }
            }
        }
        data.addAll(resColl);
//        System.out.println("После сортировки mySort: " + resColl);
    }


/*1.2 Реализовать метод public static void minSort(Collection<Integer> data) по следующему алгоритму
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую*/
    public static void minSort(Collection<Integer> data){
//        System.out.println("До сортировки: " + data);
        List<Integer> helpColl = new ArrayList<>();
        while (!data.isEmpty()){
            helpColl.add(Collections.min(data));
            data.remove(Collections.min(data));
        }
        data.addAll(helpColl);
//        System.out.println("После сортировки minSort: " + data);
    }


//1.3 Реализовать метод public static void collSort(Collection<Integer> data) используя метод sort из Collections
    public static void collSort(Collection<Integer> data){
//        System.out.println("До сортировки: " + data);
        List<Integer> resColl = new ArrayList<>(data);
        resColl.sort(null);
        data.clear();
        data.addAll(resColl);
//        System.out.println("После сортировки collSort: " + resColl);
    }


//1.4 Реализовать метод public static Collection<String> compareSort()
// в котором сравнить производительность методов и вернуть их имена,
// отсортированные в порядке производительности, первый - самый быстрый.
// В случае равенства производительности каких-то методов, возвращать их названия в алфавитном порядке.
    public static Collection<String> compareSort(){
        long startTime;   //Переменные для учета времени
//        long methodTime = new Date().getTime();

        //Создание тестовой коллекции
        final int ELEMENTS_COUNT = 100;
        List<Integer> testCollection = new ArrayList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            testCollection.add(i);
        }
        Collections.shuffle(testCollection);
        List<Integer> testCollection1 = new ArrayList(testCollection);
        List<Integer> testCollection2 = new ArrayList(testCollection);
        List<Integer> testCollection3 = new ArrayList(testCollection);

        //Вспомогательная коллекция для автосортировки
        TreeSet<Method> resultTreeSet = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(Method o1, Method o2) {
                if (o1.time != o2.time) {
                    return Long.compare(o1.time, o2.time);
                } else {
                    int out = 0;
                    int varLimit = o1.name.length() - (o1.name.length() - o2.name.length());
                    for (int i = 0; i < varLimit; i++) {
                        if (o1.name.charAt(i) != o2.name.charAt(i)) {
                            out = Integer.compare(o1.name.charAt(i), o2.name.charAt(i));
                            break;
                        }
                    }
                    return out;
//                    return Integer.compare(o1.name.charAt(0), o2.name.charAt(0));
                }
            }
        });

        //Блок тестирования
        startTime = new Date().getTime();
        mySort(testCollection1);
        long methodTime1 = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime1, "mySort"));
//        System. out.println("Сортировка mySort: " + methodTime1);

        startTime = new Date().getTime();
        minSort(testCollection2);
        long methodTime2 = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime2, "minSort"));
//        System. out.println("Сортировка minSort: " + methodTime2);

        startTime = new Date().getTime();
        collSort(testCollection3);
        long methodTime3 = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime3, "collSort"));
//        System. out.println("Сортировка collSort: " + methodTime3);

        //Подготовка к выводу результата. Создаю коллекцию на основе объектов String
        Collection<String> resColl = new ArrayList<>();
        for (Method method : resultTreeSet) {
            resColl.add(method.name);
        }
        return resColl;
    }

    static class Method{ //implements Comparable{
        private long time;
        private String name;

        public Method(long time, String name) {
            this.time = time;
            this.name = name;
        }

    }



}
