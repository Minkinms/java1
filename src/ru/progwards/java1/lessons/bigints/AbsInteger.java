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

        int int_1 = 50_000;
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

    //Статический метод вычисления суммы потомков
    static AbsInteger add(AbsInteger num1, AbsInteger num2){
        /*Ниже приведено два варианта
        Второй вариант появился после сомнения в правильности моего понимания задания (т.к. в позапрошлом задании намудрил,
        теперь страраюсь читать и сомневаться :-) )

         */

    /* Вариант 1.
    Для реализации метода ввел в класс родителя 3 метода, которые призваны объединить потомков:
    AbsInteger addNumbers(AbsInteger addedNumber), int getMaxValue() и long getValue().
        Метод addNumbers() переопределяется в каждом классе потомке для работы с числами определенных типов.
        С помощью getMaxValue() определяю какая из складываемых переменных относится к типу с большим диапазоном.
        Это необходимо для корректного использования addNumbers().
        (Работа ведется только с целыми числами).
        long getValue() используется в классах потомках для получения значений складываемых чисел.
        Использую long как наибольшее хранилище (Например, если придется добавить класс LongInteger). Далее см. в ByteInteger.

     */
        /*
        if (num1.getMaxValue() >= num2.getMaxValue()){
            return num1.addNumbers(num2);
        }else {
            return num2.addNumbers(num1);
        }

         */

        /*
        Вариант 2.
        Использую только метод getValue(). Получаю значения переменных классов потомков, приведенных к int.
        Затем анализирую на соответствие диапазонам типов данных и по результату создаю соответствующий объект.
        */
        int result = num1.getValue() + num2.getValue();     //Вспомогательная переменная для наглядности
        if (result <= Byte.MAX_VALUE && result >= Byte.MIN_VALUE){
            return new ByteInteger((byte) result);
        }else {
            if (result <= Short.MAX_VALUE && result >= Short.MIN_VALUE){
                return new ShortInteger((short) result);
            }else {
                return new IntInteger(result);
            }
        }

    }

    //Ввожу метод для связи всех потомков и возможности его переопределения потомками
    // для выполнения операций с разными типами переменных
    AbsInteger addNumbers(AbsInteger addedNumber){
        return null;
    }

    //Необходим для варианта 1
    int getMaxValue(){
        return 0;
    }

    //Метод для возвращения значения переменной класса потомка.
    //Т.к синтаксис требует указать тип данных, указываю int, как максимальный в задании.
    int getValue(){
        return 0;
    }

    //В одном из вариантов при поиске решения использовал equals, но т.к. складывать подразумевается числа разных типов, от использования отказался
    //Если сравиниваемый объект не пустой и того же класса, то true
    /*@Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

     */

}
