package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(3);
        BigInteger number = new BigInteger("235");
        System.out.println(Arrays.toString(arrayInteger.digits));
        arrayInteger.fromInt(number);
        System.out.println(Arrays.toString(arrayInteger.digits));
        System.out.println(arrayInteger.toInt() + "\n");

        ArrayInteger aInt_1 = new ArrayInteger(3);
        BigInteger number_1 = new BigInteger("711");
        aInt_1.fromInt(number_1);
        arrayInteger.add(aInt_1);
        System.out.println(Arrays.toString(arrayInteger.digits));

    }

    //Переменные класса
    byte[] digits;  //массив для хранения числа

    //Конструктор класса
    ArrayInteger(int n){
        digits = new byte[n];   //Инициализирую массив длиной n
    }

    //Метод для записи числа в массив
    public void fromInt(BigInteger value){
        //Подразумеваю, что в массиве уже задана подходящая для числа длина
        //Направшивается проверка
        for (int i = 0; i < digits.length; i++){
            //Для заполнения массива использую деление с остатком на 10.
            //В операции использую константу BigInteger.TEN
            //и метод для приведения значения BigInteger к типу byte
            digits[i] = value.remainder(BigInteger.TEN).byteValue();
            value = value.divide(BigInteger.TEN);
        }
    }

    //Метод для извлечения числа из массива
    BigInteger toInt(){
        BigInteger numberFromArray = new BigInteger("0");   //Создаю вспомогательную переменную. Её буду возвращать
        BigInteger helpNumber;  //Вторая вспомогательная переменная для читаемости (можно вставить в выражение напрямую)
        for (int i = 0; i < digits.length; i++ ){
            //Возвожу 10 в нужную степень для дальнейшего умножения на соответствующее число
            helpNumber = BigInteger.TEN.pow(i);
            //К текущему значению numberFromArray прибавляю новый объект BigInteger, созданный от числа в массиве.
            //Этот же объект умножаю на helpNumber, которое является десяткой возведенной в соответствующую степень
            numberFromArray = numberFromArray.add((BigInteger.valueOf(digits[i])).multiply(helpNumber));
        }
        return numberFromArray;
    }

    //Метод для сложения объектов ArrayInteger
    boolean add(ArrayInteger num){
        //Проверяю длины массивов, т.к. больший не удастся положить в меньший
        if (this.digits.length >= num.digits.length){
            //Т.к. прибавляемый массив может быть меньшей длины, ограничение задаю по нему.
            for (int i = 0; i < num.digits.length; i++){
                //Если сумма значений массивов меньше 9, просто складываем
                if ((this.digits[i] + num.digits[i]) < 10){
                    this.digits[i] += num.digits[i];
                }else {
                    //Если сумма значений массивов больше 9, записываем остаток от деления в текущую ячейку и
                    //прибавляем 1 к значению в следующей.
                    //Прибавляем только если работаем не с последней ячейкой. Если сумма выходит за ределы, то возвращаем false
                    this.digits[i] =(byte)  ((this.digits[i] + num.digits[i]) % 10);
                    if (i == this.digits.length - 1){
                        System.out.println("Переполнение массива");
                        Arrays.fill(this.digits, (byte) 0);
                        return false;
                    } else {
                        this.digits[i + 1] += 1;
                    }

                }
            }
            return true;
        }else{
            System.out.println("Сложение выполнить невозможно. Прибавляемое число большей разрядности");
            return false;
        }

    }
}
