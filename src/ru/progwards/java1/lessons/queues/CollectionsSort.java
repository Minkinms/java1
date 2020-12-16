package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void main(String[] args) {
        Collection<Integer> data1 = new ArrayList<>();
        Collection<Integer> numbers = new ArrayDeque<>();
        numbers.add(3); //index 0
        numbers.add(4); //index 1
        numbers.add(2); //index 2
        numbers.add(5); //index 3
        numbers.add(1); //index 4

//        System.out.println("До сортировки, numbers: " + numbers);
//        mySort(numbers);
//        minSort(numbers);
//        collSort(numbers);
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
        for (int i = 0; i < resColl.size(); i++) {
            for (int j = i + 1; j < resColl.size(); j++) {
                if (resColl.get(i) > resColl.get(j)) {
                    Collections.swap(resColl, i, j);//Замена чисел местами
                }
            }
        }
        System.out.println("После сортировки mySort: " + resColl);
    }


/*1.2 Реализовать метод public static void minSort(Collection<Integer> data) по следующему алгоритму
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую*/
    public static void minSort(Collection<Integer> data){
//        System.out.println("До сортировки: " + data);
        List<Integer> resColl = new ArrayList<>(data);
        List<Integer> helpColl = new ArrayList<>();
        while (!resColl.isEmpty()){
            helpColl.add(Collections.min(resColl));
            resColl.remove(Collections.min(resColl));
        }
        resColl.addAll(helpColl);
        System.out.println("После сортировки minSort: " + resColl);
    }


//1.3 Реализовать метод public static void collSort(Collection<Integer> data) используя метод sort из Collections
    public static void collSort(Collection<Integer> data){
        System.out.println("До сортировки: " + data);
        List<Integer> resColl = new ArrayList<>(data);
        resColl.sort(null);
        System.out.println("После сортировки collSort: " + resColl);
    }


//1.4 Реализовать метод public static Collection<String> compareSort()
// в котором сравнить производительность методов и вернуть их имена,
// отсортированные в порядке производительности, первый - самый быстрый.
// В случае равенства производительности каких-то методов, возвращать их названия в алфавитном порядке.
    public static Collection<String> compareSort(){
        var startTime = new Date().getTime();   //Переменные для учета времени
        long methodTime = new Date().getTime();

        //Создание тестовой коллекции
        final int ELEMENTS_COUNT = 2500;
        List<Integer> testCollection = new ArrayList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            testCollection.add(i);
        }
        Collections.shuffle(testCollection);

        //Вспомогательная коллекция для автосортировки
        TreeSet<Method> resultTreeSet = new TreeSet<>(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return Long.compare(o1.time, o2.time);
            }
        });

        //Блок тестирования
        startTime = new Date().getTime();
        mySort(testCollection);
        methodTime = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime, "mySort"));
//        System. out.println("Сортировка mySort: " + methodTime);

        startTime = new Date().getTime();
        minSort(testCollection);
        methodTime = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime, "minSort"));
//        System. out.println("Сортировка minSort: " + (new Date().getTime() - startTime));

        startTime = new Date().getTime();
        collSort(testCollection);
        methodTime = new Date().getTime() - startTime;
        resultTreeSet.add(new Method(methodTime, "collSort"));
//        System. out.println("Сортировка collSort: " + (new Date().getTime() - startTime));

        //Подготовка к выводу результата. Создаю коллекцию на основе объектов String
        Collection<String> resColl = new ArrayList<String>();
        for (Method method : resultTreeSet) {
            resColl.add(new String(method.name));
        }
        return resColl;
    }

    static class Method{
        private long time = 0;
        private String name = "";

        public Method(long time, String name) {
            this.time = time;
            this.name = name;
        }
    }



}
