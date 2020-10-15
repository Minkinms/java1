package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight {
    public static void main(String[] args) {
        Food food_1 = new Food(2);
        Food food_2 = new Food(5);

        System.out.println(food_1.compareWeight(food_2));
        System.out.println(food_2.compareWeight(food_2));
        System.out.println(food_2.compareWeight(food_1));
    }

    //Переменные класса
    int weight;     //Вес еды в граммах

    //Конструктор класса
    public Food (int weight){
        this.weight = weight;
    }

    //Возвращение значения веса
    public int getWeight(){
        return weight;
    }

    //Переопределение метода интерфейса
    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeight) {
        Food smthFood = (Food) smthHasWeight;
        return switch (Double.compare(getWeight(), smthFood.weight)) {
            case 1 -> CompareResult.GREATER;
            case -1 -> CompareResult.LESS;
            case 0 -> CompareResult.EQUAL;
            default -> null;
        };
    }
}
