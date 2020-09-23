package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static void main(String[] args) {
       /*
        //Тест maxSide()
        System.out.println("Тест maxSide()");
        System.out.println(maxSide(1, 3, 5));     //c>b>a
        System.out.println(maxSide(3, 1, 5));     //a>b<c
        System.out.println(maxSide(5, 3, 1));     //a>b>c
        System.out.println(maxSide(1, 5, 3));     //b>c>a
        System.out.println(maxSide(3, 5, 1));     //b>a>c
        System.out.println(maxSide(1, 1, 5));     //a=b<c
        System.out.println(maxSide(1, 5, 5));     //a<b=c
        System.out.println(maxSide(5, 5, 5));     //a=b=c

        //Тест minSide()
        System.out.println("Тест minSide()");
        System.out.println(minSide(1, 3, 5));     //c>b>a
        System.out.println(minSide(5, 3, 1));     //a>b>c
        System.out.println(minSide(1, 5, 3));     //b>c>a
        System.out.println(minSide(3, 5, 1));     //b>a>c
        System.out.println(minSide(1, 1, 5));     //a=b<c
        System.out.println(minSide(1, 1, 1));     //a=b=c

        //Тест isEquilateralTriangle()
        System.out.println("Тест isEquilateralTriangle()");
        System.out.println(isEquilateralTriangle(1, 3, 5));     //c>b>a
        System.out.println(isEquilateralTriangle(5, 3, 1));     //a>b>c
        System.out.println(isEquilateralTriangle(1, 5, 3));     //b>c>a
        System.out.println(isEquilateralTriangle(3, 3, 3));     //a=b=c


        */
    }

    //Определение наибольшей длины сторон треугольника
    public static int maxSide(int a, int b, int c) {
        int maxSide = 0;
           //Реализована последовательная проверка значений разницы длин сторон с условием выполнения обоих равенств
           if ((a - b) >= 0  && (a - c) >= 0) {
               maxSide = a;
           }
           if ((b - a) >= 0  && (b - c) >= 0) {
               maxSide = b;
           }
           if ((c - a) >= 0  && (c - b) >= 0) {
               maxSide = c;
           }

        return maxSide;
    }

    //Определение наименьшей длины сторон треугольника
    public static int minSide(int a, int b, int c) {
        int minSide = 0;
        //Реализована последовательная проверка значений разницы длин сторон с условием выполнения обоих равенств
        if ((a - b) <= 0  && (a - c) <= 0) {
            minSide = a;
        }
        if ((b - a) <= 0  && (b - c) <= 0) {
            minSide = b;
        }
        if ((c - a) <= 0  && (c - b) <= 0) {
            minSide = c;
        }

        return minSide;
    }

    //Определение равностороннего треугольника
    public static boolean isEquilateralTriangle(int a, int b, int c) {
        boolean triangleIsEquilateral;
        //Реализована последовательная проверка равенства сторон с условием выполнения обоих равенств
        if ((a == b) && (a == c)) {
            triangleIsEquilateral = true;
        } else {
            triangleIsEquilateral = false; //если  одно из условий не выполнено, то значение false
        }

        return triangleIsEquilateral;
    }

}
