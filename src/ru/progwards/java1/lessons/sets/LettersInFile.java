package ru.progwards.java1.lessons.sets;

/*Реализовать класс, считывающий содержимое файла и возвращающего набор букв,
которые встречались в этом файле.
Буквы, это латинские [A..Z[ и [a..z] и русские [А..Я] и [а..я],
остальные символы надо игнорировать

public static String process(String fileName) - вернуть все буквы,
        которые встретились в файле, сконкатенированные в виде строки.
        Буквы должны быть упорядочены по алфавиту, типа “ADEF...”.
        Все возникающие исключения, по работе с потоками, пробросить выше.
        */


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.TreeSet;

public class LettersInFile{
    public static void main(String[]args){
       // C:\Java\Progwards\HW 04.12.2020 задания до 13\src\ru\progwards\java1\lessons\sets\File.txt
        try {
            String fileName = "C:\\Java\\Progwards\\HW 04.12.2020 задания до 13\\src\\ru\\progwards\\java1\\lessons\\sets\\File.txt";
            process(fileName);
            System.out.println(process(fileName));
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static String process(String fileName) throws IOException {
        try(RandomAccessFile rafCensor = new RandomAccessFile(fileName, "r")) {  //Поток для чтения и записи в файл
            byte[] bytes = new byte[(int) rafCensor.length()];      //Массив байт для чтения из файла
            rafCensor.read(bytes);                                  //Считывание массива байт из файла
            String textFromFile = new String(bytes);                //Создание строки для дальнейшей обработки
            char[] charsFromFile = textFromFile.toCharArray();

            TreeSet<Character> charsTreeSet = new TreeSet<>(new Comparator<Character>(){
                @Override
                public int compare(Character o1, Character o2) {
                    return Character.compare(o1, o2);
                }
            });

            for(Character characterFromArray:charsFromFile){
                if(Character.isAlphabetic(characterFromArray)){
                    charsTreeSet.add(characterFromArray);
                }

            }
            System.out.println(charsTreeSet.toString());
            StringBuilder stringBuilder = new StringBuilder();
            for (char symbol:charsTreeSet){
                stringBuilder.append(symbol);
            }
            System.out.println(stringBuilder);
           return stringBuilder.toString();
        }

        //return ;
    }


}
