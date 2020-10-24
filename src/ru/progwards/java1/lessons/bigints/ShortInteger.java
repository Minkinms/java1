package ru.progwards.java1.lessons.bigints;

 class ShortInteger extends AbsInteger{
    //Переменные класса
    short mShortNumber;

    //Конструктор класса
    ShortInteger(short mShortNumber){
        this.mShortNumber = mShortNumber;
    }

    //Переопределяю метод родителя
    @Override
    AbsInteger addNumbers(AbsInteger addedNumber) {
            /*Возвращаю новый объект-потомок AbsInteger.
            В качестве аргумента число, приведенное к short, которое является суммой
            переменной класса "this.mByteNumber" и значения, полученного из addedNumber методом getValue().
            Значение из getValue() возвращается long, но т.к. по условию нужно вернуть значение более подходящего типа,
            то long приводим в данном случае к short.

             */
        return new ShortInteger ((short) (this.mShortNumber + (short) addedNumber.getValue()));
    }

    @Override
    public String toString() {
        return Short.toString(mShortNumber);
    }

     @Override
     int getMaxValue() {
         return Short.MAX_VALUE;
     } //Возвращаю максимальное значение хранимого типа данных

     @Override
     long getValue() {
         return mShortNumber;
     } //Возвращаю значение переменной класса потомка.
}
