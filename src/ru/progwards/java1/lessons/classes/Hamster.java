package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal{

    //Конструктор класса. Задаем вес также как и в родительском
    public Hamster (double weight) {
        super(weight);
    }

    //Переопределение метода выбора животного
    @Override
    public Animal.AnimalKind getKind() {
        return AnimalKind.HAMSTER;
    }

    //Переопределение метода выбора еды
    @Override
    public Animal.FoodKind getFoodKind() {
        return FoodKind.CORN;
    }

    //Переопределение коэффициента веса еды к весу тела животного.
    @Override
    public double getFoodCoeff() {
        return 0.03;
    }
}
