package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static void main(String[] args) {
        //String fileName = "Lines.txt";
        String fileName = "G:/Java/Progwards/Test/Test_30.10.2020/src/ru/progwards/java1/lessons/io1/Lines.txt";
        System.out.println(calcEmpty(fileName));

    }

    //Метод для подсчета пустых строк в файле
    public static int calcEmpty(String fileName)  {
        int countEmptyLines = 0;    //счетчик строк

        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            try {
                while (scanner.hasNextLine()){
                    String strFromFile = scanner.nextLine();
                    if (strFromFile.isEmpty()){
                        //System.out.println("Пустая");
                        countEmptyLines++;
                    }
                }
            } finally {
                reader.close();
            }

        } catch (IOException io){
            return -1;
        }
        return countEmptyLines;
    }
}
