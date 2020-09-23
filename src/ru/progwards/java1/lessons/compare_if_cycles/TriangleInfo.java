package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static void main(String[] args) {
       /*
        //Тест isTriangle()
        System.out.println("Тест isTriangle()");
        System.out.println(isTriangle(1, 2, 3));
        System.out.println(isTriangle(1, 1, 1));
        System.out.println(isTriangle(2, 2, 3));
        System.out.println(isTriangle(2, 2, 5));
        System.out.println(isTriangle(5, 3, 7));

        //Тест isRightTriangle()
        System.out.println("Тест isRightTriangle()");
        System.out.println(isRightTriangle(1 ,1, 2));
        System.out.println(isRightTriangle(2 ,2, 3));
        System.out.println(isRightTriangle(3 ,4, 5));   //Пифагорова тройка
        System.out.println(isRightTriangle(5 ,12, 13)); //Пифагорова тройка

        //Тест isIsoscelesTriangle()
        System.out.println("Тест isIsoscelesTriangle()");
        System.out.println(isIsoscelesTriangle(1, 2, 3));
        System.out.println(isIsoscelesTriangle(1, 1, 1));
        System.out.println(isIsoscelesTriangle(2, 2, 3));
        System.out.println(isIsoscelesTriangle(5, 2, 2));
        System.out.println(isIsoscelesTriangle(3 ,4, 5));   //Пифагорова тройка


        */

    }

    //Можно ли построить треугольник
    public static boolean isTriangle(int a, int b, int c) {
        boolean triangleConstruct;
        //Реализована последовательная проверка отношений сторон с условием выполнения всех равенств
        if (a < (b + c) && b < (a + c) && c < (a + b)) {
            triangleConstruct = true;
        } else {
            triangleConstruct = false;
        }

        return triangleConstruct;
    }

    //Определение прямоугольного треугольника
    public static boolean isRightTriangle(int a, int b, int c) {
        boolean triangleIsRight;
        //Реализована последовательная проверка теоремы Пифагора с условием выполнения хотя бы одного равенства
        if ( a*a == (b*b + c*c) || c*c == (a*a + b*b) || b*b == (a*a + c*c)) {
            triangleIsRight = true;
        } else {
            triangleIsRight = false;
        }

        return  triangleIsRight;
    }

    //Определение равнобедренного треугольника
    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        boolean triangleIsIsosceles;
        //Реализована последовательная проверка равенства сторон с условием выполнения хотя бы одного равенства
        //Также добавлено дополнительное условие проверки для исключения равностороннего треугольника
        if ((a == b || a == c || c == b) && !((a == b) && (a == c))){
            triangleIsIsosceles = true;
        } else {
            triangleIsIsosceles = false;
        }

        return triangleIsIsosceles;
    }

}

