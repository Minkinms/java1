package ru.progwards.java1.lessons.io2;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Censor {
    public static void main(String[] args) {
        try {
            String inoutFileName = "C:\\Jav\\Progwards\\HW 01.11.2020\\src\\ru\\progwards\\java1\\lessons\\io2\\Censor_text1.txt";
            String[] obscene = {"мглою", "крутя", "зверь", "завоет", "дитя"};
            censorFile(inoutFileName, obscene);
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    //Метод для замены задаваемых слов на "*"
    public static void censorFile(String inoutFileName, String[] obscene) {
        try(RandomAccessFile rafCensor = new RandomAccessFile(inoutFileName, "rw")){  //Поток для чтения и записи в файл
            byte[] bytes = new byte[(int) rafCensor.length()];      //Массив байт для чтения из файла
            rafCensor.read(bytes);                                  //Считывание массива байт из файла
            String textFromFile = new String(bytes);                //Создание строки для дальнейшей обработки

            if (obscene != null){                                   //Проверка
                for (String str:obscene){                                      //Перебор массива исключаемых слов
                    textFromFile = textFromFile.replaceAll(str, stars(str));    //Замена слов на *
                }
            }

            rafCensor.seek(0);                                  //Перевод курсора
            rafCensor.write(textFromFile.getBytes());               //Запись в файл массива байт из строки
            rafCensor.setLength(rafCensor.getFilePointer());        //Отсекание остатка файла.
            //Долго разбирался, советовался как решить проблему.
            //В файле оставался "хвост" из символов. После обновленной лекции по FileReader,
            //подтвердилось подозрение, связанное с количеством байт занимаемых разными символами.


        }catch (IOException ioExc){
            throw new CensorException(inoutFileName, ioExc.getMessage());
        }

    }

    //Метод для определения количества знаков "*" для замены слова
    public static String stars(String word){
        return "*".repeat(word.length());
    }


    //Класс исключения
    public static class CensorException extends RuntimeException{       //Сд алал на основе RuntimeException по примеру в лекции
        String fileName = "";
        String stringException = "";

        public CensorException(String fileName, String stringException){
            this.fileName = fileName;
            this.stringException = stringException;
        }

        @Override
        public String toString() {
            return fileName + ":" + stringException;
        }
    }

}
