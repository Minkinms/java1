package ru.progwards.java1.lessons.queues;

public class Order {
    public static void main(String[] args) {

    }

//    Создать класс - очередь, на обслуживание заявок клиентов в зависимости от величины суммы заказа.
//2.2 Создать приватное свойство double sum  - сумма заказа
//2.3 Создать приватное свойство int num  - номер заказа
//2.4 Создать конструктор public Order(double sum) - для номера заказа создать систему автонумерации, начиная с 1
//2.5 Создать геттер public double getSum()
//2.6 Создать геттер public int getNum()

    //Переменные класса
    private double sum; //сумма заказа
    private int priority = 0;
    private int num = 0;  //номер заказа




    //Конструктор класса
    public Order(double sum) {
        this.sum = sum;
//        num++;
    }

    public double getSum() {
        return sum;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", priority=" + priority +
                ", number=" + num +
                '}';
    }
}
