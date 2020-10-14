package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {

    //Результаты сравнения
    public enum CompareResult{
        LESS,
        EQUAL,
        GREATER
    }

    //Метод для сравнения животных по весу
    public CompareResult compareWeight(CompareWeight smthHasWeight);

    //Сортировка
    public static void sort(CompareWeight[] a){
        CompareResult result; //вспомогательная переменная для наглядности

        //Вспомогательный массив для хранения промежуточного значения
        CompareWeight[] helpArray = new CompareWeight[1];

        //Цикл сортировки из ArraySort
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
               //Отличие от ArraySort. Для сравнения элементов массива используем метод интерфейса,
                //который точно будет переопределен и результат его будет из вариантов CompareResult
               result =  a[i].compareWeight(a[j]);
                if (result == CompareResult.GREATER) {  // реализация "if (a[i] > a[j])"
                    helpArray[0] = a[i];
                    a[i] = a[j];
                    a[j] = helpArray[0];

                }
            }
        }

    }

}
