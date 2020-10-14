package ru.progwards.java1.lessons.interfaces;

//import ru.progwards.java1.lessons.classes.Cow;
//import ru.progwards.java1.lessons.classes.Duck;
//import ru.progwards.java1.lessons.classes.Hamster;

import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight {



    public static void main(String[] args) {
        //Animal a = new Animal(20);
        //System.out.println(a.toString());

        //Cow cow = new Cow(120);
        //Cow cow1 = new Cow(120);
        //System.out.println(cow.toStringFull());

        //Duck duck = new Duck(2.3);
        //Duck duck1 = new Duck(2.3);
        //System.out.println(duck.toStringFull());

        //Hamster hamster = new Hamster(0.5);
        //System.out.println(hamster.toStringFull());
/*
        System.out.println(cow.equals(cow1));   //Разные Cows, одинаковый вес
        System.out.println(cow1.equals(cow));   //Обратная проверка. Разные Cows, одинаковый вес
        System.out.println(cow.equals(duck1)); //Сow и Duck одного веса
        System.out.println(cow.getFood1kgPrice());
        System.out.println(duck.getFood1kgPrice());
        System.out.println(cow.getFoodPrice());

        System.out.println(cow.compareFoodPrice(duck));
        System.out.println(duck.compareFoodPrice(cow));
        System.out.println(duck.compareFoodPrice(duck));

 */
        /*System.out.println(duck.compareWeight(hamster));
        System.out.println(hamster.compareWeight(duck));
        System.out.println(duck.compareWeight(duck));
        System.out.println(duck.compareWeight(duck1));
        //System.out.println(duck.compareWeight(cat(2.0)));

         */

        CompareWeight[] array = new Animal[3];
        array[0] = new Animal(3.0);
        array[1] = new Animal(1.0);
        array[2] = new Animal(2.0);
        System.out.println(array[0] + " : " + array[1] + " : " + array[2]);
        CompareWeight.sort(array);
        System.out.println(array[0] + " : " + array[1] + " : " + array[2]);

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

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()){
            System.out.println("Объект пуст или сравнивается с объектом другого класса.");
            return false;
        }
        Animal animal = (Animal) anObject;
        return Double.compare(animal.weight, weight) == 0; //TODO: разобраться с синтаксисом == 0.
        // сравниваем с 0, получаем 0, 1, -1, возвращается True, False
        //  вероятно потому, что метод boolean
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    //Переопределение функции из интерфейса
    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(this.getFoodPrice(), animal.getFoodPrice());
    }

    //Сравнение двух животных по весу с помощью интерфейса
    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeight) {
        //Привожу объект сравнения к объекту типа Animal
// Честно говоря не совсем понятно как приводятся классы\объекты. Сделал по аналогии с equals.
//Здесь, как я понимаю, можно сравнить с любым объектом, имеющим один параметр double,
//который будет восприниматься как вес и после приведения ссылка на тип класса изменится на Animal.
//Версия подтвердилась после продолжения с Food.
        Animal smthAnimal = (Animal) smthHasWeight;
        //Сравниваю значения веса вызывающего метод и вызываемого методом
        //Синтаксис откорректировался по предложению idea
        //Это Валерий пытался показать на вебинаре
        return switch (Double.compare(getWeight(), smthAnimal.weight)) {
            case 1 -> CompareResult.GREATER;
            case -1 -> CompareResult.LESS;
            case 0 -> CompareResult.EQUAL;
            default -> null;
        };
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

    //Информация о цене еды для животного. Формула = Вес еды * стоимость кг
    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }

    //Информация о стоимости 1 кг еды животного
    public double getFood1kgPrice(){
        switch (getFoodKind()){
            case HAY: return 20.0;
            case CORN: return 50.0;
            //case FoodKind.UNKNOWN: return 0.0;
            default: return 0.0;
        }
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
        //return "I am " + getKind() + ", eat " + getFoodKind();
        return "My weight " + this.weight; //Изменил для нагладности проверки сортировки
    }

    //Определение коэффициента веса еды к весу тела животного.
    // По умолчанию для класса Animal это 0.02
    public double getFoodCoeff() {
        return 0.02;
    }

    //Расчет необходимого веса еды.
    // Заложена формула: вес_еды = вес_животного * коэффициент_веса_тела.
    public double calculateFoodWeight() {
        return this.weight * getFoodCoeff();
    }

    //Информация о животном в формате:
    //I am <AnimalKind>, eat <FoodKind> <CalculateFoodWeight>
    public String toStringFull() {
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }

}
