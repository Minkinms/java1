package ru.progwards.java1.lessons.bigints;

//import ru.progwards.java1.lessons.arrays.DIntArray;

import java.math.BigDecimal;


public class BigAlgebra {
    public static void main(String[] args) {
        //BigAlgebra bigAlgebra1 = new BigAlgebra();
        BigDecimal num1 = new BigDecimal("2");
        int pow1 = 8;
        System.out.println("Число: " + num1 + " в степени: " + pow1 + " Ответ: " + BigAlgebra.fastPow(num1, pow1));
        BigDecimal num2 = new BigDecimal("5");
        int pow2 = 5;
        System.out.println("Число: " + num2 + " в степени: " + pow2 + " Ответ: " + fastPow(num2, pow2));
        BigDecimal num3 = new BigDecimal("2.5");
        int pow3 = 4;
        System.out.println("Число: " + num3 + " в степени: " + pow3 + " Ответ: " + fastPow(num3, pow3));
        pow3 = 0;
        System.out.println("Число: " + num3 + " в степени: " + pow3 + " Ответ: " + fastPow(num3, pow3));
        pow3 = 1;
        System.out.println("Число: " + num3 + " в степени: " + pow3 + " Ответ: " + fastPow(num3, pow3));

    }

    //Быстрое возведение в степень
    //Реализация схемы "Слева направо"
    static public BigDecimal fastPow(BigDecimal num, int pow) {

        //Использую вложенный класс для оптимизации(?) и читаемости кода
        BigAlgebra.DoFastPow doFastPow = new BigAlgebra.DoFastPow(num, pow); //следую указанию лекции
                                                                             // "всегда предварять статические методы именем класса"
        switch (pow){
            case 0:  return BigDecimal.ONE;
            case 1:  return num;
            default: return doFastPow.numToPow();
        }


    }

    //Класс для перевода десятичного числа степени в массив битов и быстрого возведения числа в степень
    //использую наработки из задания Arrays,класс DIntArray
    //Класс DIntArray доработал, добавил метод getArrayLength() для определения длины массива.
    //можно было изменить доступ к массиву, но решил, что доступ к нему лучше не открывать
    static class DoFastPow extends DIntArray {
        //параметры
        int powNumber;
        BigDecimal Number;

        //Конструктор
        DoFastPow(BigDecimal num,int powNumber) {
            super();                    //Конструктор класса DIntArray, создающий массив int array[]
            this.powNumber = powNumber;
            this.Number = num;
        }

        //Метод для перевода десятичного числа степени на двоичный массив int array[]
        public void doPowArray(){
            //Наполняю массив значениями
            while (this.powNumber > 0) {
                add(this.powNumber % 2);  //Остаток от деления добавляю в конец массива array[], используя метод из DIntArray
                this.powNumber /= 2;
            }
        }

        public BigDecimal numToPow(){
            //Раскладываю десятичное число степени на двоичное
            doPowArray();

            BigDecimal result = new BigDecimal("0");    //Вспомогательная переменная.
                                                            //Инициализирую 0, другого метода не нашел

            for (int i = (getArrayLength() - 1); i >= 0; i--){

                //Определяю зоны формулы: старший бит, средние, младший бит
                //Старший бит(элемент массива).
                //Если 1, то возвожу в квадрат
                //после перевода числа всегда получается 1, поэтому не проверяю на >0
                if (i == (getArrayLength() - 1)){
                    result = result.add(this.Number);
                    result = result.multiply(result);
                }
                //Тело формулы (средние значения формулы)
                //Если бит 1, то результат умножаю на число и затем возвожу в квадрат
                if ((i != (getArrayLength() - 1)) && (i != 0)){
                    if (at(i) > 0) {
                        result = result.multiply(this.Number);
                    }
                    result = result.multiply(result);
                }
                //Младший бит (элемент массива)
                //Если бит 1, то результат только умножаю на число
                if ((i == 0) && (at(i) > 0) ) {
                    //if  {
                        result = result.multiply(this.Number);
                    //}

                }

            }


            return result;
        }

    }

}
