package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;

public class StackCalc {
    public static void main(String[] args) {
        StackCalc stackCalc = new StackCalc();
        stackCalc.push(1.0);
        stackCalc.push(2.0);
        stackCalc.push(3.0);
        stackCalc.push(4.0);
        stackCalc.push(5.0);

//        System.out.println(stackCalc.pop());
//        System.out.println(stackCalc.pop());
//        stackCalc.add();
//        stackCalc.sub();
//        stackCalc.mul();
//        stackCalc.div();
    }

/*Стековый калькулятор (есть даже такой стековый язык программирования - Forth).
 Стек, это структура данных, работающая по принципу LIFO - last in first out (последний вошедший выходит первый),
 это противоположность привычной очереди FIFO - first in first out - первый вошедший выходит первый

Реализовать class StackCalc, который содержит стек чисел с плавающей точкой (double).
 Выбрать наиболее удобную для этого коллекцию. Реализовать методы работы со стеком:*/

    //Переменные класса
    ArrayDeque<Double> mainDeque = new ArrayDeque();

//3.1 public void push(double value) - положить value на вершину стека
    public void push(double value){
        mainDeque.push(value);

    }

//3.2 public double pop() - взять (убрать) значение с вершины стека
    public double pop(){
        return mainDeque.pop();
    }

//3.3 public void add() - сложить 2 верхних значения на стеке,
// результат положить на стек. В итогу в стеке должно быть на один элемент меньше
    public void add(){
        push(pop() + pop());
    }

//3.4 public void sub() - вычесть верхнее значение на стеке,
// из следующего по глубине, результат положить на стек.
// В итоге в стеке должно быть на один элемент меньше
    public void sub(){
        double var = pop();
        push(pop() - var);
    }

//3.5 public void mul() - умножить 2 верхних значения на стеке, результат положить на стек.
// В итогу в стеке должно быть на один элемент меньше
    public void mul(){
        push(pop() * pop());
    }
//3.6 public void div() - поделить на верхнее значение на стеке, следующее по глубине, результат положить на стек.
// В итоге в стеке должно быть на один элемент меньше
    public void div(){
        double var = pop();
        push(pop() / var);
    }
}
