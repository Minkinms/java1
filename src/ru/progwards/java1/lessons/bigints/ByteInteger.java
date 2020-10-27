package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger {
    public static void main(String[] args) {
        ByteInteger bi = new ByteInteger((byte) 2);
        System.out.println(bi.getMaxValue());
        System.out.println(bi.toString());
    }

    //Переменные класса
    byte mByteNumber;

    //Конструктор класса
    ByteInteger(byte mByteNumber){
        this.mByteNumber = mByteNumber;
    }

    //Переопределяю метод родителя для складывания чисел
    @Override
    AbsInteger addNumbers(AbsInteger addedNumber) {
            /*Возвращаю новый объект-потомок AbsInteger.
            В качестве аргумента число, приведенное к byte, которое является суммой
            переменной класса "this.mByteNumber" и значения, полученного из addedNumber методом getValue().
            Значение из getValue() возвращается int, но т.к. по условию нужно вернуть значение более подходящего типа,
            то int приводим к byte.

             */
            return new ByteInteger((byte) (this.mByteNumber + (byte) addedNumber.getValue()));
    }

    @Override
    public String toString() {
        return Byte.toString(mByteNumber);
    }

    @Override
    int getMaxValue() {
        return Byte.MAX_VALUE; //Возвращаю максимальное значение хранимого типа данных
    }

    @Override
    int getValue() {
        return mByteNumber; //Возвращаю значение переменной класса потомка.

    }
}
