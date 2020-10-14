package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    public static void main(String[] args) {
        CalculateFibonacci calculateFibonacci = new CalculateFibonacci();
        System.out.println(fiboNumber(7));
        System.out.println(lastFibo.n);
        System.out.println(lastFibo.fibo);
        CalculateFibonacci calculateFibonacci_1 = new CalculateFibonacci();
        System.out.println(fiboNumber(6));
        System.out.println(lastFibo.n);
        System.out.println(lastFibo.fibo);
        System.out.println(" ");
        System.out.println(getLastFibo()); //Возвращает ссылку. Возможно переопределить toString
        clearLastFibo();                    //Использование метода для работы с переменной вложенного класса
        System.out.println(getLastFibo());
        //System.out.println(" ");

    }

    //Переменные класса
    private static CacheInfo lastFibo = new CacheInfo();    //Заработало именно с конструктором new

    //Вложенный класс
    public static class CacheInfo {
        //Переменные класса
        public int n;
        public int fibo;
    }

    //Метод для считывания переменной вложенного класса
    public static CacheInfo getLastFibo(){
        return lastFibo;
    }

    //Метод для очистки переменной вложенного класса
    public static void clearLastFibo(){
        lastFibo = null;
    }



    //Функция определения n-го числа в последовательности Фибоначчи
    public static int fiboNumber(int n){
        if (lastFibo.n == n){           //Проверяю, рассчитывалось ли для для этого числа
            return lastFibo.fibo;       //Если расчитывали, то возвращаю последнее значение
        } else {
            lastFibo.n = n;             //Если нет, то записываю последнее рассчитываемое и определяею число
            //вспомогательные переменные для реализации формулы Fn = F(n-2) + F(n-1)
            int number1 = 0;
            int number2 = 1;
            int fiboNumberN = 1; //Начальное значение 1, т.к. последовательность начинается с "1 1 ..."

            //Цикл для формирования последовательности Фибоначчи до n-го числа
            for (int i = 1; i <= n; i++) {
                if (i == 1){                            //исключение для первого числа, т.к. последовательность начинается с 1 1
                    //System.out.println(fiboNumberN);
                } else {
                    fiboNumberN = number1 + number2;    //реализация формулы Fn = F(n-2) + F(n-1)
                    //System.out.println(fiboNumberN);
                    number1 = number2;                  //обновление значения F(n-2)
                    number2 = fiboNumberN;              //обновление значения F(n-1)
                }
            }
            lastFibo.fibo = fiboNumberN;
            return fiboNumberN;
        }

    }
}
