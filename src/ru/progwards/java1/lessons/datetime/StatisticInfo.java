package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
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
}
