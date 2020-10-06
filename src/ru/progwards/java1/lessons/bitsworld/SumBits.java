package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static void main(String[] args) {
        //SumBits sum = new SumBits();
        System.out.println(sumBits((byte) 0b0101_1001));
        System.out.println(sumBits((byte) 0b1111_1111));
        System.out.println(sumBits((byte) 0b0000_0000));
    }

    //Метод суммирования битов
    public static int sumBits(byte value){
        byte checkingVar = 0b0000_0001;         //Вспомогательная переменная для побитового сравнения
        int resultSumBits = 0;                  //Переменная для записи результата
        for (int i = 1; i<= Byte.SIZE; i++){    //Цикл побитового сравнения
            if ((value & checkingVar) > 0) {    //Если результат & больше нуля, т.е. 1, то +1 к результату
                resultSumBits++;
            }
            value >>= 1;                        //для проверки следующего бита value смещаю на бит вправо.
        }
        return resultSumBits;
    }
}
