package ru.progwards.java1.lessons.compare_if_cycles;

import static ru.progwards.java1.lessons.compare_if_cycles.TriangleInfo.isIsoscelesTriangle;

public class CyclesGoldenFibo {

    public static void main(String[] args) {

        //Выполнение задания
        //Вывод ряда 15 чисел Фибоначчи
        for (int i = 1; i <= 15; i++) {
            System.out.print(fiboNumber(i) + " ");
        }
        System.out.println();

        //Поиск золотых треугольников
        for (int a = 1; a <= 100; a++){
            for (int b = 1; b <= 100; b++) {
                for (int c = 1; c <= 100; c++){
                    if (isGoldenTriangle(a, b, c)) {
                        System.out.println("Золотой треугольник со сторонами " + a + ", " + b + ", " + c);
                    }
                }
            }


        }



        //Тест containsDigit()
        System.out.println("Тест containsDigit()");
        System.out.println(containsDigit(10, 9));
        System.out.println(containsDigit(19, 9));
        System.out.println(containsDigit(99, 9));
        System.out.println(containsDigit(195678, 9));
        System.out.println(containsDigit(125078, 0));
        System.out.println(containsDigit(125078, 3));
        System.out.println(containsDigit(0, 0));


        //Тест fiboNumber()
        //System.out.println("Последовательность Фибоначчи");
        //System.out.println(fiboNumber(1));
        //System.out.println(fiboNumber(2));
        //System.out.println(fiboNumber(3));
        //System.out.println(fiboNumber(10));

        /*
        //Тест isGoldenTriangle()
        System.out.println("Проверка треугольника на золотое сечение");
        System.out.println(isGoldenTriangle(2, 5, 6));
        System.out.println(isGoldenTriangle(2, 2, 6));
        System.out.println(isGoldenTriangle(233, 233, 144));
        System.out.println(isGoldenTriangle(377, 377, 233));
        System.out.println(isGoldenTriangle(5, 5, 3));
        */
    }

    //Функция поиска цифры в числе
    public static boolean containsDigit(int number, int digit) {
        boolean digitFound = false;

        while (number >= 0) {
            if (digit == (number%10)) {   //Сравниваем цифру с остатком от деления на 10
                digitFound = true;
                break;
            }
            //Добавил после проверки роботом и указанием проблемы со значениями (0, 0)
            //Для обеспечения проверки, но исключения бесконечности цикла, дополнительно проверяю на ноль
            if (number == 0) {
                break;
            }
            number /= 10;                   //Уменьшаем число на 10 и повторяем проверку остатка
            //System.out.println(number);
        }
        //System.out.println(digitFound);
        return digitFound;
    }

    //Функция определения n-го числа в последовательности Фибоначчи
    public static int fiboNumber(int n){
        //вспомогательные переменные для реализации формулы Fn = F(n-2) + F(n-1)
        int number1 = 0;
        int number2 = 1;
        int fiboNumberN = 1; //Начальное значение 1, т.к. последовательность начинается с "1 1 ..."

        //Цикл для формирования последовательности Фибоначчи до n-го числа
        for (int i = 1; i <= n; i++) {
            if (i == 1){                      //исключение для первого числа, т.к. последовательность начинается с 1 1
                //System.out.println(fiboNumberN);
            } else {
                fiboNumberN = number1 + number2;    //реализация формулы Fn = F(n-2) + F(n-1)
                //System.out.println(fiboNumberN);
                number1 = number2;                  //обновление значения F(n-2)
                number2 = fiboNumberN;              //обновление значения F(n-1)
            }
        }

        return fiboNumberN;
    }

    //Определение золотого треугольника
    public static boolean isGoldenTriangle(int a, int b, int c) {
        boolean triangleIsGolden = false;               //переменная для записи и вывода ответа
        double GOLDEN_PROPORTION_MIN = 1.61703;         //Константы для расчета золотого сечения
        double GOLDEN_PROPORTION_MAX = 1.61903;

        //Для проверки является ли треугольник равнобедренным, использую импорт метода из класса TriangleInfo
        if (isIsoscelesTriangle(a, b, c)) {
            //Вычисляем отношение бедра к основанию
            //double proportion = (double) a / c;  //Если считать, что классически "a" и "b" стороны, а "с" основание
            double proportion = 0.0;
            //Определяем какие из трех сторон равны
            if (a == b) {
                proportion = (double) a / c;
            } else {
                if (a == c) {
                    proportion = (double) a / b;
                } else {
                    if (b == c) {
                        proportion = (double) b / a;
                    }
                }
            }
            //System.out.println(proportion);
            //Проверяем попадает ли результат в заданный константами диапазон.
            if (proportion >= GOLDEN_PROPORTION_MIN && (proportion) <= GOLDEN_PROPORTION_MAX) {
                triangleIsGolden = true;
            }
        }

        return triangleIsGolden;
    }

}
