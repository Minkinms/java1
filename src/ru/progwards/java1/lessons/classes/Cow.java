package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {

    //Конструктор класса. Задаем вес также как и в родительском
    public Cow (double weight) {
        super(weight);
    }

    //Переопределение метода выбора животного
    @Override
    public AnimalKind getKind() {
        return AnimalKind.COW;
        //return super.getKind();
    }

    //Переопределение метода выбора еды
    @Override
    public FoodKind getFoodKind() {
        return FoodKind.HAY;
    }

    //Переопределение коэффициента веса еды к весу тела животного.
    @Override
    public double getFoodCoeff() {
        return 0.05;
    }
}
