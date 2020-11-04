package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void main(String[] args) throws IOException {
        String inFileName = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task10_3_1.txt";
        String outFileName = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task10_3_2.txt";
        String filter = " -_";


        filterFile(inFileName, outFileName, filter);
    }

    //Метод для исключения из файла символов
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        boolean write = true;
        //Поток для чтения файла
        FileReader reader = new FileReader(inFileName);
        Scanner scanner = new Scanner(reader);
        //Поток для записи в файл
        FileWriter writer = new FileWriter(outFileName, true);
        try {
            while (scanner.hasNextLine()) {
                String strFromFile = scanner.nextLine();
                if (!(filter.isEmpty())){
                    for (int i = 0; i < strFromFile.length(); i++){
                        for (int c = 0; c < filter.length(); c++){
                            if (strFromFile.charAt(i) == filter.charAt(c)){
                                write = false;
                                break;
                            } else {
                                write = true;
                            }
                        }
                        if (write){
                            writer.write(strFromFile.charAt(i));
                        }
                    }
                }

            }
        } finally {
            reader.close();
            writer.close();
            //scanner.close();  //Нужно ли закрывать scanner?
        }


    }
}
