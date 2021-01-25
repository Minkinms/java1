package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;

/*Реализовать класс для ручной профилировки производительности программного кода.
Профилировка программного кода - измерение времени выполнения
отдельных фрагментов кода с целью выявления узких мест в производительности.

Секции могут быть только вложенные, как матрешки, и при этом поддержку рекурсии реализовывать не нужно.
Но каждая секция может встретиться несколько раз.*/

public class Profiler {
    public static void main(String[] args) {
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;

        Profiler profiler = new Profiler();
        StatisticInfo profStatInf = new StatisticInfo();

        long start = System.currentTimeMillis();

        profiler.enterSection("Section 1");
        for (int i = 0; i < 100000; i++){
            s1++;
            profiler.enterSection("Section 2");
            for (int a = 0; a < 100; a++){
                s2++;
                profiler.enterSection("Section 3");
                for (int b = 0; b < 10; b++){
                    s3++;
                }
                exitSection("Section 3");
            }
            exitSection("Section 2");
        }
        exitSection("Section 1");
        System.out.println("Полное время " + (System.currentTimeMillis() - start) + " мсек");

        for (StatisticInfo stInf:getStatisticInfo())
        System.out.println(stInf);
    }


    //Переменные класса
    public static Map<String, StatisticInfo> statisticInfoMap = new HashMap<>();    //Хранение информации о секциях с общим доступом
    public static Map<Integer, String> depthMap = new HashMap<>();  //Дополниетльное хранилище для определения вложенности секций

    //2.1 Реализовать методы
    //- войти в профилировочную секцию, замерить время входа.
    public static void enterSection(String name){
        if(statisticInfoMap.containsKey(name)){
            statisticInfoMap.get(name).count += 1;
            statisticInfoMap.get(name).sectionTime = (int)System.currentTimeMillis();   //Запись времени входа в секцию
        }else {
            StatisticInfo profStatInf = new StatisticInfo();        //Создание секции
            profStatInf.sectionName = name;
            profStatInf.sectionTime = (int)System.currentTimeMillis();
            profStatInf.depthSection = depthMap.size();             //Установка и запись глубины вложенности
            statisticInfoMap.put(name, profStatInf);
            depthMap.put(depthMap.size(), name);
        }
    }

    //2.2 выйти из профилировочной секции.
    // Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name){
        if(statisticInfoMap.containsKey(name)){
            int i = (int)System.currentTimeMillis() - statisticInfoMap.get(name).sectionTime;   //Определение времени прохождения секции
            statisticInfoMap.get(name).fullTime += i;                       //перезапись значения суммарного времени
            statisticInfoMap.get(name).selfTime = selfTimeCalc(name);       //вычисление чистого времени
        }
    }

    //Метод для вычисления чистого времени секции
    public static int selfTimeCalc(String name){
        if(statisticInfoMap.get(name).depthSection != depthMap.size() - 1){     //Если секция не последняя
            String upperName = depthMap.get(statisticInfoMap.get(name).depthSection + 1); //Получаем имя секции на уровень ниже, вложенной
            return statisticInfoMap.get(name).fullTime - statisticInfoMap.get(upperName).fullTime;
        }else{
            return statisticInfoMap.get(name).fullTime;     //Если последняя просто записываем одинаковое время
        }
    }

    //2.3 получить профилировочную статистику, отсортировать по наименованию секции
     public static List<StatisticInfo> getStatisticInfo(){
         List<StatisticInfo> resultList = new ArrayList<>();
         for(var value : statisticInfoMap.entrySet()){          //Собираю список из объектов секций
            resultList.add(value.getValue());
         }
        resultList.sort(new Comparator<StatisticInfo>() {           //Сортировка
            @Override
            public int compare(StatisticInfo o1, StatisticInfo o2) {
                return o1.sectionName.compareTo(o2.sectionName);
            }
        });
        return resultList;
     }

    //2.4 Реализовать class StatisticInfo
/*    class StatisticInfo{
        //Переменные класса
        public String sectionName;      //имя секции
        //Полное время выполнения секции в миллисекундах.
        public int fullTime = 0;
        //чистое время выполнения секции в миллисекундах.
        // Для вложенных секций, из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.
        public int selfTime = 0;
        //количество вызовов. В случае, если вызовов более одного,
        // fullTime и selfTime содержат суммарное время выполнения всех вызовов.
        public int count = 1;

        public int sectionTime;     //Время выполнения секции
        public int depthSection; //Внешняя секция

        @Override
        public String toString() {
            return "StatisticInfo{" +
                    "sectionName='" + sectionName + '\'' +
                    ", fullTime=" + fullTime +
                    ", selfTime=" + selfTime +
                    ", count=" + count +
                    '}';
        }
    }*/


}
