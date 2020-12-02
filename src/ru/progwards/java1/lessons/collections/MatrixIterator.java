package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    public static void main(String[] args) {
        Integer[] intArray1 = {1, 2, 3, 5, 6, 8};
        Integer[] intArray2 = {10, 20};
        Integer[] intArray3 = {100, 200, 300};
        Integer[][] intArray2D = {intArray1, intArray2, intArray3};
        String[] stringArray = {"A", "B", "C", "D"};

        MatrixIterator<Integer> arrayIterator = new MatrixIterator<>(intArray2D);
        while (arrayIterator.hasNext()){
            Integer nums = arrayIterator.next();
            System.out.println(nums);
        }
    }


    //Переменные класса
    private T[][] array;
    int index1 = 0;      //Т.к. чтение происходит только вперед, считаю индексы в свойстве класса
    int index2 = 0;
    boolean nextLine = false;   //Тег для перевода читаемой строки массива

    //Конструктор класса
    MatrixIterator(T[][] array) throws RuntimeException{
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean nextLine = (index1 + 1) == (this.array.length); //Условие: если читается последняя строка, значит следующая будет равна длине
        boolean nextSymbol = (index2 ) != (this.array[this.array.length - 1].length);//Условие: будет читаться Не последний символ
        if (nextLine){
            return nextSymbol;
        }else{
            return true;
        }
    }

    @Override
    public T next() {
        index2++;
        if (nextLine){
            index1++;
            index2 = 1;
            nextLine = false;
        }
        if (index2 == this.array[index1].length){
            nextLine = true;
        }
        return this.array[index1][index2 - 1];

    }
}
