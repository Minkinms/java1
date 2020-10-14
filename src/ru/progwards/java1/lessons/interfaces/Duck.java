package ru.progwards.java1.lessons.interfaces;

//import ru.progwards.java1.lessons.classes.Animal;

public class Duck extends Animal {

    //Конструктор класса. Задаем вес также как и в родительском
    public Duck(double weight) {
        super(weight);
    }

    //Переопределение метода выбора животного
    @Override
    public AnimalKind getKind() {
        return AnimalKind.DUCK;
        //return super.getKind();  //Так формирует система при добавлении вспомогательной функцией
    }

    //Переопределение метода выбора еды
    @Override
    public FoodKind getFoodKind() {
        return FoodKind.CORN;
    }

    //Переопределение коэффициента веса еды к весу тела животного.
    @Override
    public double getFoodCoeff() {
        return 0.04;
    }
}
