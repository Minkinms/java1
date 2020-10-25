package ru.progwards.java1.lessons.bigints;

 class IntInteger extends AbsInteger{
    //Переменные класса
    int mIntNumber;

    //Конструктор класса
    IntInteger(int mIntNumber) {
        this.mIntNumber = mIntNumber;
    }


    //Переопределяю метод родителя
    @Override
    AbsInteger addNumbers(AbsInteger addedNumber) {
            /*Возвращаю новый объект-потомок AbsInteger.
            В качестве аргумента число, приведенное к int, которое является суммой
            переменной класса "this.mByteNumber" и значения, полученного из addedNumber методом getValue().
            Значение из getValue() возвращается int, но т.к. по условию нужно вернуть значение более подходящего типа,
            то int приводим в данном случае к int, а точнее использум как есть.

             */
            return new IntInteger (this.mIntNumber + addedNumber.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(mIntNumber);
    }

     @Override
     int getMaxValue() {
         return Integer.MAX_VALUE;
     } //Возвращаю максимальное значение хранимого типа данных

     @Override
     int getValue() {
         return mIntNumber;
     } //Возвращаю значение переменной класса потомка.
}
