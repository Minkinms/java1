package ru.progwards.java1.lessons.queues;

public class Calculate {
    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(calculation2());
    }

//Class Calculate, который содержит методы:
//3.7 public static double calculation1(), возвращающую результат вычисления следующей формулы:
//  2.2*(3+12.1), используя класс StackCalc
    public static double calculation1(){
        StackCalc stackCalc = new StackCalc();
        stackCalc.push(12.1);
        stackCalc.push(3.0);
        stackCalc.add();
        stackCalc.push(2.2);
        stackCalc.mul();
        return stackCalc.pop();
    }

//3.8 public static double calculation2(), возвращающую результат вычисления следующей формулы:
//  (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
    public static double calculation2(){
        StackCalc stackCalc = new StackCalc();
//        stackCalc.push(calcPart1(stackCalc));
        calcPart1(stackCalc);   //(87+2*(13.001-9.2))
        calcPart2(stackCalc);   //(19-3.33)
        stackCalc.mul();        //calcPart2 * calcPart1
        calcPart4(stackCalc);   //(737.22+24)
        calcPart3(stackCalc);   //(55.6-12.1)
        stackCalc.div();        //calcPart4 / calcPart3
        stackCalc.add();        // "+"
        return stackCalc.pop();
    }

    private static void calcPart1(StackCalc stackCalc){
        stackCalc.push(13.001);
        stackCalc.push(9.2);
        stackCalc.sub();
        stackCalc.push(2.0);
        stackCalc.mul();
        stackCalc.push(87.0);
        stackCalc.add();
//        return stackCalc.pop();
    }

    private static void calcPart2(StackCalc stackCalc){
        stackCalc.push(19.0);
        stackCalc.push(3.33);
        stackCalc.sub();
    }

    private static void calcPart3(StackCalc stackCalc){
        stackCalc.push(55.6);
        stackCalc.push(12.1);
        stackCalc.sub();
    }

    private static void calcPart4(StackCalc stackCalc){
        stackCalc.push(737.22);
        stackCalc.push(24.0);
        stackCalc.add();
    }


}
