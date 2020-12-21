package ru.progwards.java1.lessons.queues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();
        orderQueue.add(new Order(1000));
        orderQueue.add(new Order(15000));
        orderQueue.add(new Order(10000));
        orderQueue.add(new Order(21000));
        orderQueue.add(new Order(5000));
        orderQueue.add(new Order(19000));
        orderQueue.add(new Order(31000));
        orderQueue.add(new Order(11000));
        orderQueue.add(new Order(41000));

        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
        System.out.println(orderQueue.get() + "\n");
//        orderQueue.get();
//        orderQueue.get();
//        orderQueue.get();

        while (!orderQueue.fullQueue.isEmpty()) {
            System.out.println(orderQueue.fullQueue.poll());
        }
//        for(Object order: orderQueue.fullQueue){
//            System.out.println(order.toString());
//        }

    }

    //Свойства класса
    //Основная очередь заказов
    PriorityQueue fullQueue = new PriorityQueue(new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return Integer.compare(o1.getPriority(), o2.getPriority());
        }

    });

    //Константы для определения приоритета
    final double SUM_SIZE_1 = 10000;
    final double SUM_SIZE_2 = 20000;

    //Переменная для сквозной нумерации заказов
    int orderNumber = 1;

/*    2.7 Создать метод, public void add(Order order),
размещающий заказы в очередь с приоритетом, разбивая их по 3-м классам:
3 - заказы до 10000 руб включительно
2 - заказы от 10000 до 20000 руб включительно
1 - заказы от 20000 руб*/

    public void add(Order order){
        priority(order);
        setOrderNumber(order);
        fullQueue.offer(order);
    }

    //Метод для задания номера заказа
    public void setOrderNumber(Order order){
        if(order.getNum() == 0) {
            order.setNum(this.orderNumber);
            this.orderNumber++;
        }
    }

    //Метод для определения приоритета
    public void priority(Order order){
        if(order.getSum() <= SUM_SIZE_1){
            order.setPriority(3);
        }else {
            if(order.getSum() > SUM_SIZE_1 && order.getSum() <= SUM_SIZE_2){
                order.setPriority(2);
            }else {
                if(order.getSum() > SUM_SIZE_2){
                    order.setPriority(1);
                }
            }
        }


    }

/*
    2.8 Создать метод, public Order get(),
    возвращающий первый заказ в очереди для обслуживания.
    Вначале обслуживаются заказы класса 1, потом 2, потом 3.
    Внутри каждого класса заказы должны обслуживаться в порядке поступления (по номеру заказа).
    Метод не выбрасывает исключения, возвращает null в случае пустой очереди.

    Продумать, и, при необходимости, добавить в классы нужные методы и свойства, для того,
    чтобы реализовать эту задачу.
 */

    public Order get(){
        //Очередь заказов по нумерации в пределах приоритета
        PriorityQueue orderNumberQueue = new PriorityQueue(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.compare(o1.getNum(), o2.getNum());
            }

        });

//        long hightPriority;

        if(!fullQueue.isEmpty()) {
            Order firstOrder = (Order) fullQueue.peek();    //TODO:Добавить проверку на isEmpty
            long hightPriority = firstOrder.getPriority();


            while (!fullQueue.isEmpty()) {
                Order nextOrder = (Order) fullQueue.peek();
                if (nextOrder.getPriority() == hightPriority) {
                    orderNumberQueue.offer(fullQueue.poll());
                } else break;
            }
        }

        Order resultGetOrder = (Order) orderNumberQueue.poll();


            fullQueue.addAll(orderNumberQueue);


/*        while (!orderNumberQueue.isEmpty()) {
            System.out.println(orderNumberQueue.poll());

        }
        System.out.println(" ");*/
        return resultGetOrder;
    }
}
