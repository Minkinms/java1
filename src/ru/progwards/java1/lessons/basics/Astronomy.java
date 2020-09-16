package ru.progwards.java1.lessons.basics;

public class Astronomy {
    public static void main(String[] args) {

    }

    //Константы
    static final double PI = 3.14;                  //Число Пи
    static final double RADIUS_EARTH = 6_371.2;     //Радиус Земли
    static final double RADIUS_MERCURY = 2_439.7;   //Радиус Меркурия
    static final double RADIUS_JUPITER = 71_492.0;  //Радиус Юпитера

    //Вычисление площади поверхности сферы
    public static Double sphereSquare(Double r) {
        return 4 * PI * Math.pow(r, 2);
    }

    //Вычисление площади поверхности Земли
    public static Double earthSquare(){
        return sphereSquare(RADIUS_EARTH);
    }

    //Вычисление площади поверхности Меркурия
    public static Double mercurySquare(){
        return sphereSquare(RADIUS_MERCURY);
    }

    //Вычисление площади поверхности Юпитера
    public static Double jupiterSquare(){
        return sphereSquare(RADIUS_JUPITER);
    }

    //Отношение площади поверхности Земли к Меркурию
    public static Double earthVsMercury(){
        return earthSquare() / mercurySquare();
    }

    //Отношение площади поверхности Земли к Юпитеру
    public static Double earthVsJupiter(){
        return earthSquare() / jupiterSquare();
    }

}
