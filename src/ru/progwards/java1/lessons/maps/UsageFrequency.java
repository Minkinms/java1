package ru.progwards.java1.lessons.maps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

//Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов. Методы:
//2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно использовать wiki.test.tokens
public class UsageFrequency {
    public static void main(String[] args) {
        try {
//            String fileName = "G:\\Java\\Progwards\\Homework 10.01.2020\\src\\ru\\progwards\\java1\\lessons\\maps\\Censor_text1.txt";
            String fileName = "G:\\Java\\Progwards\\Homework 10.01.2020\\src\\ru\\progwards\\java1\\lessons\\maps\\wiki.test.tokens";
            UsageFrequency usageFrequency = new UsageFrequency();
            usageFrequency.processFile(fileName);
//            System.out.println(Arrays.toString(usageFrequency.charsFromFile));
            usageFrequency.getLetters();
            usageFrequency.getWords();
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    //Переменные класса
    private char[] charsFromFile;       //Массив символов из файла
    String textFromFile;
    // 2.1 загрузить содержимое файла
    public void processFile(String fileName) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {  //Поток для чтения файла
            byte[] bytes = new byte[(int) raf.length()];      //Массив байт для чтения из файла
            raf.read(bytes);
            textFromFile = new String(bytes);
            charsFromFile = textFromFile.toCharArray();   //Создание массива символов из файла на основе строки, созданной из массива байт
        }
    }

    // 2.2 вернуть Map, который содержит все найденные буквы и цифры, и количество раз,
    // которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др не учитывать.
    public Map<Character, Integer> getLetters(){
        Map<Character, Integer> lettersMap = new HashMap<>();
            for(Character character:charsFromFile){
                if(Character.isAlphabetic(character) || Character.isDigit(character)){ //Перебираю массив символов по признаку "это буква" или " это цифра"
                    if(lettersMap.containsKey(character)){
                        lettersMap.put(character, lettersMap.get(character) + 1);
                    }else {
                        lettersMap.put(character, 1);
                    }
                }
            }
        return lettersMap;
    }

//    2.3  - вернуть Map, который содержит все найденные слова и количество раз,
//    которое каждое слово встретилось. Знаки препинания, такие как “.,!? @” и др являются разделителями.
    public Map<String, Integer> getWords(){
        Map<String, Integer> wordsMap = new HashMap<>();
        StringBuilder stringSymbols = new StringBuilder();

        for(Character character:charsFromFile){
            if(Character.isLetter(character)){ //Перебираю массив символов по признаку "это буква" или " это цифра"
                stringSymbols.append(character);
            }else stringSymbols.append(" ");
        }
//        System.out.println(stringSymbols);

//        String[] array = stringSymbols.toString().split("\\s*,\\s*");
//        System.out.println(Arrays.toString(array));
        try(Scanner scanner = new Scanner(stringSymbols.toString())) {
            while (scanner.hasNext()) {
                String word = scanner.next();
//                System.out.println(word);
                if(wordsMap.containsKey(word)){
                    wordsMap.put(word, wordsMap.get(word) + 1);
                }else {
                    wordsMap.put(word, 1);
                }
            }
        }

        return wordsMap;
    }
}



