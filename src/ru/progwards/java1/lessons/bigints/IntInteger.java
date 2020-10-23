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

        //Проверка складываемых объектов на соответствие классов с помощью equals и вывод результата
        if (equals(addedNumber)){
            //System.out.println("Объекты равны по классу");

            //Возвращаю новый объект-потомок AbsInteger.
            // В качестве аргумента число, приведенное к byte, которое является суммой
            //переменной класса "this.mByteNumber" и такой же переменной из addedNumber, приведенного к ShortInteger
            return new IntInteger ((short) (this.mIntNumber + ((IntInteger) addedNumber).mIntNumber));
        } else{
            System.out.println("Объекты НЕ равны по классу");
            return null;
        }

    }

    //Если сравиниваемый объект не пустой и того же класса, то true
    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public String toString() {
        return Integer.toString(mIntNumber);
    }
}
