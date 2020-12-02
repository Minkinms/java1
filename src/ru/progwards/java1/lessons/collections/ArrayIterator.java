package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T>  implements Iterator<T> {
    public static void main(String[] args) {
        Integer[] intArray = {1, 3, 2, 5, 10, 13};
        String[] stringArray = {"A", "B", "C", "D"};
//        ArrayIterator<Integer> arrayIterator = new ArrayIterator<>(intArray);
//        while (arrayIterator.hasNext()){
//            Integer num = arrayIterator.next();
//            System.out.println(num);
//        }
        ArrayIterator<String> arrayIterator = new ArrayIterator<>(stringArray);
        while (arrayIterator.hasNext()){
            String num = arrayIterator.next();
            System.out.println(num);
        }
        Integer[] ar = null;
        ArrayIterator<Integer> arrayIterator1 = new ArrayIterator<>(ar);
        try{
            arrayIterator1.next();
        }catch (RuntimeException re){
            System.out.println("массив null");
        }

    }

    //Переменные класса
    private T[] array;
    int index = 0;      //Т.к. чтение происходит только вперед, считаю индексы в свойстве класса

    //Конструктор класса
    ArrayIterator(T[] array) throws RuntimeException{
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < this.array.length;
    }

    @Override
    public T next() {                   //TODO: Обработать исключение?
        index++;
        return this.array[index - 1];
    }
}
