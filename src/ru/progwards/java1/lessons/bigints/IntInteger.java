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
            Значение из getValue() возвращается long, но т.к. по условию нужно вернуть значение более подходящего типа,
            то long приводим в данном случае к int.

             */
            return new IntInteger ((int) this.mIntNumber + (int) addedNumber.getValue());
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
     long getValue() {
         return mIntNumber;
     } //Возвращаю значение переменной класса потомка.
}
