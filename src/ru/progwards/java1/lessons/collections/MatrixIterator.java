package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    public static void main(String[] args) {
        Integer[] intArray1 = {1, 3, 2, 5, 10, 13};
        Integer[] intArray2 = {10, 30, 20, 50, 100, 130};
        Integer[][] intArray2D = {intArray1, intArray2};
        String[] stringArray = {"A", "B", "C", "D"};

        MatrixIterator<Integer> arrayIterator = new MatrixIterator<>(intArray2D);
        while (arrayIterator.hasNext()){
            System.out.println(arrayIterator.next());
        }
    }


    //Переменные класса
    private T[][] array;
    int index1 = 0;      //Т.к. чтение происходит только вперед, считаю индексы в свойстве класса
    int index2 = 0;

    //Конструктор класса
    MatrixIterator(T[][] array) throws RuntimeException{
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        if (index1 <= this.array.length){

        }
        return true;
    }

    @Override
    public T next() {
        index1++;
        return this.array[index1 - 1][0];
    }
}
