package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static void main(String[] args) {
        System.out.println(checkBit((byte) 0b0000_1001, 0));
        System.out.println(checkBit((byte) 0b0000_1000, 3));
        System.out.println(checkBit((byte) 0b0010_1000, 8));
    }

    //Определение значения бита по порядковому номеру
    public static int checkBit(byte value, int bitNumber) {
        byte checkingVar = 0b0000_0001;
        //сдвигаю переменную на заданное количство бит, смещая нужный бит к нулевой позиции
        value >>= bitNumber;
        //Проверяю на совпадение с 1
        if ((value & checkingVar) > 0) {    //Если результат & больше нуля, возвращаю 1
            return 1;
        } else {
            return 0;
        }
    }
}
