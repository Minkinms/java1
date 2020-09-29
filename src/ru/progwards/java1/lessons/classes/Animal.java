package ru.progwards.java1.lessons.classes;

public class Animal {

    public static void main(String[] args) {
        Animal a = new Animal(20);
        System.out.println(a.toString());

        Cow cow = new Cow(100);
        System.out.println(cow.toStringFull());

        Duck duck = new Duck(2.3);
        System.out.println(duck.toStringFull());

        Hamster hamster = new Hamster(0.5);
        System.out.println(hamster.toStringFull());
    }

    //Переменные класса
    double weight;              //Вес животного

    //Конструктор класса
    //Вес животного
    public Animal(double weight) {
        this.weight = weight;           //Сохранение веса животного в переменную класса
    }

    public double getWeight() {
        return this.weight;
    }

    //перечесление животных
    enum AnimalKind {
        ANIMAL,
        COW,
        HAMSTER,
        DUCK
    }

    //Вид еды
    enum FoodKind {
        UNKNOWN,
        HAY,
        CORN
    }

    //Выбор животного
    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }

    //Выбор еды
    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }

    //Информация о животном в формате: I am <AnimalKind>, eat <FoodKind>
    public String toString(){
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    //Определение коэффициента веса еды к весу тела животного.
    // По умолчанию для класса Animal это 0.02
    public double getFoodCoeff() {
        return 0.02;
    }

    //Расчет необходимого веса еды.
    // Заложена формула: вес-еды = вес-животного * коэффициент веса тела.
    public double calculateFoodWeight() {
        return this.weight * getFoodCoeff();
    }

    //Информация о животном в формате:
    //I am <AnimalKind>, eat <FoodKind> <CalculateFoodWeight>
    public String toStringFull() {
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }

}
