package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {

    //Константы
    static final double PI_double = 3.14;           //Число Пи
    static final float PI_float = 3.14F;            //Число Пи
    static final double RADIUS_EARTH = 6_371.2;     //Радиус Земли


    public static void main(String[] args) {

    }

    //Объем шара с Double
    public static double volumeBallDouble(double radius){
        return (double) 4 / 3 * PI_double * Math.pow(radius, 3);
    }

    //Объем шара с Float
    public static double volumeBallFloat(float radius){
        return (float) 4 / 3 * PI_float * Math.pow(radius, 3);
    }

    //Разница Double/Float
    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float)radius);
    }

}
