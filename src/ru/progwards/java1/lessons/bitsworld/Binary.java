package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public static void main(String[] args) {
        Binary test = new Binary((byte)1);
        System.out.println(test.toString());

        Binary test1 = new Binary((byte) 0b1111_1111);
        System.out.println(test1.toString());

        Binary test2 = new Binary((byte) 0b0000_0000);
        System.out.println(test2.toString());

        Binary test3 = new Binary((byte) -128);
        System.out.println(test3.toString());
    }

    //Переменные класса
    String strBinaryNumber = "";    //Для хранения результата
    byte byteNumber;                //Для хранения отображаемого числа

    //Конструктор класса
    public Binary(byte num){
        this.byteNumber = num;
        numberToBinary();
    }

    @Override
    public String toString() {          //Возвращает двоичное представление числа Byte
        return strBinaryNumber;
    }

    public void numberToBinary(){
        byte checkingVar = 0b0000_0001;         //Вспомогательная переменная для побитового сравнения
        for (int i = 0; i < Byte.SIZE; i++){    //Цикл побитового сравнения
            if ((this.byteNumber & checkingVar) > 0) {    //Если результат & больше нуля, т.е. 1, то +1 к результату
                this.strBinaryNumber = "1" + this.strBinaryNumber; //1 слева, чтобы набирать результат с права налево
            } else {
                if ((this.byteNumber & checkingVar) == 0)
                    this.strBinaryNumber = "0" + this.strBinaryNumber;
            }
            this.byteNumber >>= 1;              //для проверки следующего бита value смещаю на бит вправо.
        }
    }

}
