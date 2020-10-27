package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(3);
        BigInteger number = new BigInteger("235");
        System.out.println(Arrays.toString(arrayInteger.digits));
        arrayInteger.fromInt(number);
        System.out.println(Arrays.toString(arrayInteger.digits));
        System.out.println(arrayInteger.toInt() + "\n");

        ArrayInteger aInt_1 = new ArrayInteger(3);
        BigInteger number_1 = new BigInteger("711");
        aInt_1.fromInt(number_1);
        arrayInteger.add(aInt_1);
        System.out.println(Arrays.toString(arrayInteger.digits));

    }

    //Переменные класса
    byte[] digits;

    //Конструктор класса
    ArrayInteger(int n){
        digits = new byte[n];
    }

    public void fromInt(BigInteger value){
        //BigInteger helpNumber = new BigInteger("0");
        //helpNumber = value;
        for (int i = 0; i < digits.length; i++){
            digits[i] = value.remainder(BigInteger.TEN).byteValue();
            value = value.divide(BigInteger.TEN);
        }
    }

    BigInteger toInt(){
        BigInteger numberFromArray = new BigInteger("0");
        BigInteger helpNumber;
        // = new BigInteger.valueOf()
        for (int i = 0; i < digits.length; i++ ){
            //System.out.println(digits[i]);
            helpNumber = BigInteger.TEN.pow(i);
            numberFromArray = numberFromArray.add((BigInteger.valueOf(digits[i])).multiply(helpNumber));
        }
        return numberFromArray;
    }

    boolean add(ArrayInteger num){
        if (this.digits.length >= num.digits.length){
            for (int i = 0; i < this.digits.length; i++){
                if ((this.digits[i] + num.digits[i]) < 10){
                    this.digits[i] += num.digits[i];
                }else {
                    this.digits[i] =(byte)  ((this.digits[i] + num.digits[i]) % 10);
                    if (i == this.digits.length - 1){
                        System.out.println("Переполнение массива");
                        Arrays.fill(this.digits, (byte) 0);
                        return false;
                    } else {

                        this.digits[i + 1] += 1;
                    }

                }

                //this.digits[i] += num.digits[i];
            }
            return true;
        }else{
            System.out.println("Сложение выполнить невозможно. Прибавляемое число большей разрадности");
            return false;
        }

    }
}
