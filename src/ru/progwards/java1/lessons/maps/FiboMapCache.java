package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class FiboMapCache {
    public static void main(String[] args) {
        test();
    }

    //Переменные класса
    private Map<Integer, BigDecimal> fiboCache = new Hashtable<>();
    private boolean cacheOn;

    //Конструктор
    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }

    //в функции проверить, находится ли вычисленное значение для n в кэше,
    // и если да - вернуть его из кэша, если нет - рассчитать и добавить в кэш.
    // Учитывать значение переменной cacheOn
    public BigDecimal fiboNumber(int n){
//        System.out.println(fibonacci(n));

        if(cacheOn){
            if (!fiboCache.containsKey(n)) {
                if(n > 2){
                    fiboCache.put(n,fiboCache.get(n-1).add(fiboCache.get(n-2))); //Расчитываю и добавляю в кэш
                }else{
                    fiboCache.put(1, new BigDecimal("1"));
                    fiboCache.put(2, new BigDecimal("1"));
                }
            }
            return fiboCache.get(n);  //Возвращаю значение числа Фибоначчи в BigDecimal по ключу n
        }else {
            return fibonacci(n);               //Если кэш выключен, просто считаем
        }

    }

//    Реализовать метод public void clearCahe() который устанавливает переменную fiboCache в null
    public void clearCahe(){
        fiboCache.clear();
    }

//    Для проверки работы реализовать public static void test() -
//    тест для расчета чисел Фибоначчи от n = 1 до 1000 включительно
//    и замерить разницу во времени с on = true и on = false,
//    результат вывести на экран в формате "fiboNumber cacheOn=??? время выполнения ???"
//    для cacheOn=true и cacheOn=false, вместо ??? вывести реальные значения в мсек.
    public static void test(){
        FiboMapCache fiboMapCache = new FiboMapCache(true);
        long start = new Date().getTime();
        for(int i = 1; i <= 1000; i++){
            fiboMapCache.fiboNumber(i);
        }
        System.out.println("fiboNumber cacheOn=" + fiboMapCache.cacheOn + " время выполнения " + ((long) new Date().getTime() - start));

/*
        FiboMapCache fiboMapCache1 = new FiboMapCache(true);
        start = new Date().getTime();
        for(int i = 1; i <= 1000; i++){
            fiboMapCache1.fiboNumber(i);
        }
        System.out.println("fiboNumber cacheOn=" + fiboMapCache1.cacheOn + " время выполнения " + ((long) new Date().getTime() - start));
*/

        FiboMapCache fiboMapCache2 = new FiboMapCache(false);
        start = new Date().getTime();
        for(int i = 1; i <= 1000; i++){
            fiboMapCache2.fiboNumber(i);
        }
        System.out.println("fiboNumber cacheOn=" + fiboMapCache2.cacheOn + " время выполнения " + ((long) new Date().getTime() - start));

    }


    //Функция определения n-го числа в последовательности Фибоначчи в BigDecimal (было BigInteger)
    static public BigDecimal fibonacci(int n){
        //вспомогательные переменные для реализации формулы Fn = F(n-2) + F(n-1)
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("1");
        BigDecimal fiboNumberN = new BigDecimal("1"); //Начальное значение 1, т.к. последовательность начинается с "1 1 ..."

        //Цикл для формирования последовательности Фибоначчи до n-го числа
        for (int i = 1; i <= n; i++) {
            if (i == 1){                      //исключение для первого числа, т.к. последовательность начинается с 1 1
                //System.out.println(fiboNumberN);
            } else {
                fiboNumberN = number1.add(number2);     //реализация формулы Fn = F(n-2) + F(n-1)
                number1 = number2;                      //обновление значения F(n-2)
                number2 = fiboNumberN;                  //обновление значения F(n-1)
            }
        }

        return fiboNumberN;

    }

}