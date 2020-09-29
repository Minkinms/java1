package ru.progwards.java1.lessons.classes;

public class ComplexNum {

    public static void main(String[] args) {
        ComplexNum complexA = new ComplexNum(50, 10);
        ComplexNum complexB = new ComplexNum(2, 6);
        ComplexNum complexC = new ComplexNum(20, 9);

        System.out.println(complexA.toString());
        System.out.println(complexB.toString());

        System.out.println(complexA.add(complexB));
        System.out.println(complexB.sub(complexC));
        System.out.println(complexA.mul(complexB));
        System.out.println(complexA.div(complexC));

    }

    //переменные класса
    int realPartNumber;         //Действительная часть числа
    int imaginaryPartNumber;    //Мнимая часть числа


    //Конструктор класса
    public ComplexNum(int a, int b) {
        this.realPartNumber = a;
        this.imaginaryPartNumber = b;
    }

    @Override
    public String toString() {
        //Условие для корректного отображения числа с отрицательной мнимой частью
        if (this.imaginaryPartNumber >= 0) {
            return this.realPartNumber + "+" + this.imaginaryPartNumber + "i";
        } else {
            return this.realPartNumber + "" + this.imaginaryPartNumber + "i";
        }

    }

    //метод для сложения комплексных чисел
    //используется формула (a + bi) + (c + di) = (a + c) + (b + d)i
    public ComplexNum add(ComplexNum num) {
        //Введены вспомогательные переменные для работы с вещественной и мнимой частями
        //Можно вставить сразу в return, оставлено для унификации со сложными выражениями
        int addRealPart = this.realPartNumber + num.realPartNumber;
        int addImaginaryPart = this.imaginaryPartNumber + num.imaginaryPartNumber;

        //Результат вывожу в формате ComplexNum, поэтому для записи результата создаю новый экземпляр ComplexNum
        return new ComplexNum(addRealPart, addImaginaryPart);
    }

    //метод для вычитания комплексных чисел
    //используется формула (a + bi) - (c + di) = (a - c) + (b - d)i
    public ComplexNum sub(ComplexNum num) {
        //Введены вспомогательные переменные для работы с вещественной и мнимой частями
        //Можно вставить сразу в return, оставлено для унификации со сложными выражениями
        int addRealPart = this.realPartNumber - num.realPartNumber;
        int addImaginaryPart = this.imaginaryPartNumber - num.imaginaryPartNumber;

        //Результат вывожу в формате ComplexNum, поэтому для записи результата создаю новый экземпляр ComplexNum
        return new ComplexNum(addRealPart, addImaginaryPart);
    }

    //метод для умножения комплексных чисел
    //используется формула (a + bi) * (c + di) = (a*c - b*d) + (b*c + a*d)i
    public ComplexNum mul(ComplexNum num) {
        //Введены вспомогательные переменные для работы с вещественной и мнимой частями
        int addRealPart = this.realPartNumber * num.realPartNumber -
                          this.imaginaryPartNumber * num.imaginaryPartNumber;
        int addImaginaryPart = this.imaginaryPartNumber * num.realPartNumber +
                               this.realPartNumber * num.imaginaryPartNumber;

        //Результат вывожу в формате ComplexNum, поэтому для записи результата создаю новый экземпляр ComplexNum
        return new ComplexNum(addRealPart, addImaginaryPart);
    }

    //метод для деления комплексных чисел
    //используется формула (a + bi) / (c + di) = (a*c + b*d)/(c*c+d*d) + ((b*c - a*d)/(c*c+d*d))i
    public ComplexNum div(ComplexNum num) {
        //Введены вспомогательные переменные для работы с вещественной и мнимой частями
        int addRealPart = (this.realPartNumber * num.realPartNumber +
                this.imaginaryPartNumber * num.imaginaryPartNumber) /
                (num.realPartNumber * num.realPartNumber + num.imaginaryPartNumber * num.imaginaryPartNumber);
        int addImaginaryPart = (this.imaginaryPartNumber * num.realPartNumber -
                               this.realPartNumber * num.imaginaryPartNumber) /
                (num.realPartNumber * num.realPartNumber + num.imaginaryPartNumber * num.imaginaryPartNumber);

        //Результат вывожу в формате ComplexNum, поэтому для записи результата создаю новый экземпляр ComplexNum
        return new ComplexNum(addRealPart, addImaginaryPart);
    }
}
