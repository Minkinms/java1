package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Coder {
    public static void main(String[] args) {
        String fileName = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task10_2_1.txt";
        String outFileName = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task10_2_3.txt";
        char[] code = new char[50];
        String logName = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task_log.txt";

        //Блок считывания кода из файла
        String fileNameCode = "C:/Java/Progwards/HW 01.11.2020/src/ru/progwards/java1/lessons/io1/Task10_2_2.txt";
        try {
            FileReader reader = new FileReader(fileNameCode);
            Scanner scanner = new Scanner(reader);
            try {
                //int i =0;
                while (scanner.hasNextLine()){
                    String strFromFile = scanner.nextLine();
                    for (int i=0; i < strFromFile.length(); i++){
                        code[i] = strFromFile.charAt(i);
                    }

                }
            } finally {
                reader.close();
            }

        } catch (IOException io){
            System.out.println(io.getMessage());
        }
        //System.out.println();
        System.out.println("Код шифра:" + Arrays.toString(code));

        codeFile(fileName, outFileName, code, logName);
        //System.out.println(calcEmpty(fileName));
    }

    //Запись сообщения об ошибке в logFile
    public static void msgToLog(String logName, String msg){
        try {
            FileWriter writeToLog = new FileWriter(logName, true);
            try{
                writeToLog.write(msg + "\n");
            }finally {
                writeToLog.close();
            }
        }catch (IOException ignored){   //Подразумеваю, что с этим файлом проблем не будет

        }
    }

    //Метод для кодировки файла
    public static void codeFile (String inFileName, String outFileName, char[] code, String logName) {
        byte[] arrayBytesFromFile;
            try {
                FileInputStream bytesFromFile = new FileInputStream(inFileName);    //Байтовый поток для чтения
                FileWriter writeToFile = new FileWriter(outFileName);   //Символьный поток для записи
                try{
                    arrayBytesFromFile = bytesFromFile.readAllBytes();
                    //System.out.println("Байты из файла:" + Arrays.toString(arrayBytesFromFile));
                    //System.out.print("Символы файла в шестнадцатиричном формате: ");
                    for (byte b : arrayBytesFromFile){
                        //System.out.printf("%x ", b);
                        //Запись в выходной файл
                        writeToFile.write(code[b]);
                    }
                } //catch (RuntimeException re){
                    //msgToLog(logName, re.getMessage());
                //}
                finally {
                    bytesFromFile.close();
                    writeToFile.close();
                }
            //Обрабатываю исключения по вводу и возможные проблемы в процессе исполнения
            //Как пример использовал недостаточную емкость массива code
            }catch (IOException | RuntimeException exception){
                //System.out.println(ioe.getMessage());
                msgToLog(logName, exception.getMessage());
            }



    }
}
