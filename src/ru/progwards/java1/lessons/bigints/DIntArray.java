package ru.progwards.java1.lessons.bigints;

import java.util.Arrays;

public class DIntArray {
    /*
    public static void main(String[] args) {
        DIntArray testObjArray = new DIntArray();

        //System.out.println(testObjArray);
        testObjArray.add(1);
       // System.out.println(testObjArray.toString());
        testObjArray.add(3);
        //System.out.println(testObjArray.toString());
        testObjArray.add(4);
        //System.out.println(testObjArray.toString());
        testObjArray.add(10);
        System.out.println(testObjArray.toString());

        testObjArray.atInsert(1, 0);
        System.out.println(testObjArray.toString());
        testObjArray.atInsert(0, 5);
        System.out.println(testObjArray.toString());
        testObjArray.atInsert(3, 111);
        System.out.println(testObjArray.toString());

        testObjArray.atDelete(3);
        System.out.println(testObjArray.toString());
        testObjArray.atDelete(1);
        System.out.println(testObjArray.toString());

        System.out.println(testObjArray.at(0));
        System.out.println(testObjArray.at(1));
        System.out.println(testObjArray.at(2));
        System.out.println(testObjArray.at(15));
    }

     */

    //Переменные класса
    private int[] array;
    //public int[] array;
    //Конструктор класса
        public DIntArray(){
          array = new int[0];    //Определяю исходный массив размером 0
        }

    //Метод для добавления элемента в конец массива
    public void add(int num){
        //System.out.println(num);
        //Вариант с промежуточным массивом
        /* int[] addArray = Arrays.copyOf(array, array.length + 1);
        addArray[addArray.length - 1] = num;
        this.array = Arrays.copyOf(addArray, addArray.length);  */

        //Путем экспериментов пришел к тому, что можно переопределить массив массивом с другой длиной!
        //В данном случае копирую сам в себя, но заявляю больший размер
        this.array = Arrays.copyOf(this.array, this.array.length + 1);
        this.array[this.array.length - 1] = num;
    }

    //Метод для добавления элемента в указанную позицию массива
    public void atInsert(int pos, int num){
        //System.out.println("Добавляю в позицию " + pos + " число " + num);

        // Переопределяю массив копированием сам в себя, но с размером +1
        //В конце записан 0
        this.array = Arrays.copyOf(this.array, this.array.length + 1);
        //Сдвигаю элементы и освобождаю заданную позицию.
        //Меняя местами элементы, двигаю элементы от конца к позиции. Предпоследний на последний и т.д.
        //В нужную позицию смещается 0
        for (int i = this.array.length - 1; i > pos ; i-- ){
            this.array[i] = this.array[i] + this.array[i - 1];
            this.array[i - 1] = this.array[i] - this.array[i - 1];
            this.array[i] = this.array[i] - this.array[i - 1];
        }
        //Записываю число в позицию
        this.array[pos] = num;
    }

    //Метод для удаления элемента в указанной позиции массива
    public void atDelete(int pos){
        //System.out.println("Удаляю элемент в позиции " + pos);
        //Меняю элементы местами и перемещаю элемент с заданной позиции на конечную
        for (int i = pos; i < this.array.length - 1 ; i++ ){
            this.array[i] = this.array[i] + this.array[i + 1];
            this.array[i + 1] = this.array[i] - this.array[i + 1];
            this.array[i] = this.array[i] - this.array[i + 1];
        }
        //System.out.println(Arrays.toString(this.array));
        //Для удаления элемента переопределяю массив с размером -1, отрезая/удаляя последний элемент
        this.array = Arrays.copyOf(this.array, this.array.length - 1);
    }

    //Метод для возвращения элемента по индексу pos.
    public int at(int pos){
            // Проверка задаваемого индекса
        if (pos >= 0 && pos < this.array.length) {
            return this.array[pos];
        } else {
            System.out.println("Такого индекса нет в массиве. Длина массива ");
            return this.array.length;
        }
    }

    public int getArrayLength(){
         return this.array.length;
    }

    @Override
    public String toString(){
        return Arrays.toString(array);
    }
}
