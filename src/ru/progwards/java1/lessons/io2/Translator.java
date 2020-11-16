package ru.progwards.java1.lessons.io2;

import java.io.IOException;
import java.util.Arrays;



//Простой переводчик
public class Translator {
/*    public static void main(String[] args) throws Exception {
        String[] eng = {"hello", "world", "no", "i", "eat", "yes", "do", "it", "yourself" }; //9 слов
        String[] rus = {"привет", "мир", "нет", "я", "ем", "да", "делай", "это", "сам"};   //9 слов
        String var = null;
*//*        String text = "Hello!";
//        String s = text.toUpperCase();
        System.out.println(text.toUpperCase());
//        text = s.toLowerCase();
        System.out.println(text.toLowerCase());*//*

        Translator translator = new Translator(eng, rus);   //Перевод с eng на rus
        System.out.println(translator.translate("Hello world!"));
        System.out.println(translator.translate("No, I eat."));
        System.out.println(translator.translate("Yes, do it Yourself."));
//        System.out.println(translator.translate(var));

    }*/

    //Переменные класса
    private String[] inLang;
    private String[] outLang;

    //Конструктор класса
     public Translator(String[] inLang, String[] outLang) throws Exception {
//         if (inLang != null && outLang != null && (inLang.length == outLang.length)){
            try {
                this.inLang = Arrays.copyOf(inLang, inLang.length);
                this.outLang = Arrays.copyOf(outLang, outLang.length);
            } catch (Exception e){
                throw new Exception("Словари пусты или не соответствуют друг другу");
            }
//         } else {
             //throw new Exception("Словари пусты или не соответствуют друг другу");
//         }

    }

    //Переводик
    public String translate(String sentence) throws Exception {
        StringBuilder tranSent = new StringBuilder();   //Переведенное предложение

//        if (sentence != null) { //Проверка
        try {
            String[] arrayFromSentence = sentence.split(" ");     //Массив слов из предложения, разделитель пробел

            for (String word : arrayFromSentence) {                //Перебор слов
                Word nextWord = new Word(word);
                nextWord.capLetterToLowerCase();            //Определение регистра первой буквы слова
                nextWord.cutPunctuation();                  //Определение наличия пунктуации

                //Поиск слова в словаре и его замена
                for (int i = 0; i < inLang.length; i++) {
                    if (nextWord.toString().equals(inLang[i])) {
                        //System.out.println("Нашлось в словаре");
                        char[] charsOut = outLang[i].toCharArray();     //Вспомогательный массив
                        charsOut[0] = nextWord.capLetterToUpperCase(charsOut[0]);
                        charsOut = nextWord.addPunctuation(charsOut);
                        tranSent.append(charsOut, 0, charsOut.length);

                        if (!word.equals(arrayFromSentence[arrayFromSentence.length - 1])) { //Добавление пробела между словами
                            tranSent.append(" ");
                        }
                        break;
                    }

                    if (i == inLang.length - 1) {
                        throw  new Exception("Слово отсутствует в словаре");
                    }

                }

            }

            return tranSent.toString();

        }catch (Exception exception){
            throw  new Exception("Ошибка ввода!");
        }
//        } else {
//            //throw  new Exception("Ошибка ввода!");
//            //tranSent.append("Ошибка ввода!");
//        }


    }

/*---------------------------------------------------------------------------*/
    //Класс для работы со словом
    public static class Word{
        //Переменные класса
        char[] wordChars;                       //Набор символов слова
        boolean isCapitalLetter = false;        //Наличие заглавной буквы
        boolean isPunctuation = false;          //Наличие пунктуации
        char    punctSymbol;                    //Символ пунктуации

        //Конструктор класса
        public Word(String word) throws Exception {
            if (word != null) {
                wordChars = word.toCharArray();
            }else {
                throw new Exception("Word == null");
            }
        }

        //Метод для перевода в нижний регистр заглавной буквы
        public void capLetterToLowerCase() throws Exception{
            try{
                if (Character.isAlphabetic(wordChars[0]) &&                 //Буква?
                        !Character.isDigit(wordChars[0]) &&                 //не цифра?
                        Character.isUpperCase(wordChars[0])){           //верхний регистр?

                    wordChars[0] = Character.toLowerCase(wordChars[0]);
                    isCapitalLetter = true;
                }
            }catch (Exception e){
                throw new Exception("Ошибка символа");
            }


        }

        //Метод для перевода в верхний регистр заглавной буквы
        public char capLetterToUpperCase(char symbol) throws Exception{
            try {
                if (isCapitalLetter){
                    symbol = Character.toUpperCase(symbol);
                }
                return symbol;
            }catch (Exception e){
                throw new Exception("Ошибка символа");
            }


        }

        //Метод для проверки наличия пунктуации в конце слова.
        //При наличии - удаляется из массива символов wordChars и запоминается в punctSymbol
        public void cutPunctuation() throws Exception{
            try{
                if (!Character.isAlphabetic(wordChars[wordChars.length - 1]) &&     //(не буква и не цифра)?
                        !Character.isDigit(wordChars[wordChars.length - 1])){
                    isPunctuation = true;
                    punctSymbol = wordChars[wordChars.length - 1];                  //Сохранение символа
                    wordChars = Arrays.copyOf(wordChars, wordChars.length - 1);  //Пересобираю слово без знака пунктуации в конце
                } else{
                    isPunctuation = false;
                }
            }catch (Exception e){
                throw new Exception("Ошибка символа");
            }

        }

        //Метод для восстановления пунктуации
        public char[] addPunctuation(char[] array) throws Exception {
            try{
                if (isPunctuation){
                    array = Arrays.copyOf(array, array.length + 1);
                    array[array.length - 1] = punctSymbol;
                }
                return array;
            }catch (Exception e){
                throw new Exception("Ошибка массива");
            }

        }

       @Override
        public String toString() {
            return String.valueOf(wordChars);
        }
    }

}
