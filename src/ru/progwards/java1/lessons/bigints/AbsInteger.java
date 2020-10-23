package ru.progwards.java1.lessons.bigints;

public class AbsInteger {
    public static void main(String[] args) {
        byte b = 7;
        byte b1 = 18;
        ByteInteger byteInt = new ByteInteger(b);
        ByteInteger byteInt_1 = new ByteInteger(b1);
        //System.out.println(byteInteger.toString());
        //System.out.println(byteInteger.getClass());

        short sh = 20;
        short sh_1 = 120;
        ShortInteger shortInt = new ShortInteger(sh);
        ShortInteger shortInt_1 = new ShortInteger(sh_1);

        int int_1 = 10_000;
        int int_2 = 1234;
        IntInteger intInt = new IntInteger(int_1);
        IntInteger intInt_1 = new IntInteger(int_2);

        System.out.println("Сложение byteInt: " + add(byteInt, byteInt_1) + "\n");
        System.out.println("Сложение byteInt и shortInt: " + add(byteInt_1, shortInt) + "\n");
        System.out.println("Сложение shortInt: " + add(shortInt, shortInt_1) + "\n");
        System.out.println("Сложение shortInt и byteInt: " + add(shortInt, byteInt_1) + "\n");
        System.out.println("Сложение intInt: " + add(intInt, intInt_1) + "\n");
        System.out.println("Сложение intInt и shortInt: " + add(intInt, shortInt) + "\n");

    }

   // int defualtVar;

    //AbsInteger(int defualtVar){
    //    this.defualtVar = defualtVar;
    //}

    //AbsInteger(int defualtVar){
        //this.defualtVar = defualtVar;
    //}

    //Статический метод вычисления суммы потомков
    static AbsInteger add(AbsInteger num1, AbsInteger num2){
       if (num1.equals(num2)){
           return num1.addNumbers(num2);
       } else
           //System.out.println("Объекты НЕ равны по классу");
           if (num1.getMaxValue() > num2.getMaxValue()){
               return num1.addNumbers(num2);
           }else {
               return num2.addNumbers(num1);
           }
           //return num1;
    }

    //Ввожу метод для связи всех потомков и возможности его переопределения потомками
    // для выполнения операций с разными типами переменных
    AbsInteger addNumbers(AbsInteger addedNumber){
        return null;
    }

    int getMaxValue(){
        return 0;
    }

     long getValue(){
        return 0;
    }

    //Если сравиниваемый объект не пустой и того же класса, то true
    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    //Вероятно стоит добавить проверку складываемых чисел на попадание в диапазон переменных
    //Судя по всему будем изучать exception и делать это дальше

}
