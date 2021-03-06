package ru.progwards.java1.lessons.io2;

//import java.io.IOException;
import java.util.Arrays;



//Простой переводчик
public class Translator {
    public static void main(String[] args) {
        String[] eng = {"hello", "world", "no", "i", "eat", "yes", "do", "it", "yourself" }; //9 слов
        String[] rus = {"привет", "мир", "нет", "я", "ем", "да", "делай", "это", "сам"};   //9 слов
        String var = null;
//        String text = "Hello!";
//        String s = text.toUpperCase();
//        System.out.println(text.toUpperCase());
//        text = s.toLowerCase();
//        System.out.println(text.toLowerCase());*//*

        Translator translator = new Translator(eng, rus);   //Перевод с eng на rus
        System.out.println(translator.translate("Hello world!"));
        System.out.println(translator.translate("No, I eat."));
        System.out.println(translator.translate("Yes, do it - Yourself."));
//        System.out.println(translator.translate(var));

    }

    //Переменные класса
    private String[] inLang;
    private String[] outLang;

    //Конструктор класса
     public Translator(String[] inLang, String[] outLang)  {
         if (inLang != null && outLang != null &&
                    inLang.length == outLang.length){
             this.inLang = Arrays.copyOf(inLang, inLang.length);
             this.outLang = Arrays.copyOf(outLang, outLang.length);
         }else{
             System.out.println("Словари пусты или не соответствуют по размеру");
         }

    }

    //Переводик
    public String translate(String sentence) {
        StringBuilder tranSent = new StringBuilder();   //Переведенное предложение
        if (!sentence.equals("")) {
            String[] arrayFromSentence = sentence.split(" ");     //Массив слов из предложения, разделитель пробел

            for (String word : arrayFromSentence) {                //Перебор слов
                Word nextWord = new Word(word);
                if (nextWord.isNotWord()) {
                    tranSent.append(nextWord.toString());
                    if (!word.equals(arrayFromSentence[arrayFromSentence.length - 1])) { //Добавление пробела между словами
                        tranSent.append(" ");
                    }
                } else {
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
                            System.out.println("Слово не нашлось в словаре");
                        }

                    }
                }

            }
        }else{
            System.out.println("Предложение не содержит слов");
        }
            return tranSent.toString();
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
            public Word(String word) {
                wordChars = word.toCharArray();
        }

        //Метод для определения понятия "слово"
        //Если длина 1 символ, это не буква и не цифра, то это символ пунктуации (тире)
        public boolean isNotWord(){
            return wordChars.length == 1 &&
                    !Character.isAlphabetic(wordChars[0]) &&
                        !Character.isDigit(wordChars[0]);
        }

        //Метод для перевода в нижний регистр заглавной буквы
        public void capLetterToLowerCase() {
            if (Character.isAlphabetic(wordChars[0]) &&                 //Буква?
                    !Character.isDigit(wordChars[0]) &&                 //не цифра?
                        Character.isUpperCase(wordChars[0])){           //верхний регистр?
                wordChars[0] = Character.toLowerCase(wordChars[0]);
                isCapitalLetter = true;
            }
        }

        //Метод для перевода в верхний регистр заглавной буквы
        public char capLetterToUpperCase(char symbol) {
            if (symbol != 0){
                if (isCapitalLetter){
                    symbol = Character.toUpperCase(symbol);
                }
            }else {
                System.out.println("Символ не задан");
            }
            return symbol;
        }

        //Метод для проверки наличия пунктуации в конце слова.
        //При наличии - удаляется из массива символов wordChars и запоминается в punctSymbol
        public void cutPunctuation() {
            if (!Character.isAlphabetic(wordChars[wordChars.length - 1]) &&     //(не буква и не цифра)?
                        !Character.isDigit(wordChars[wordChars.length - 1])){
                    isPunctuation = true;
                    punctSymbol = wordChars[wordChars.length - 1];                  //Сохранение символа
                    wordChars = Arrays.copyOf(wordChars, wordChars.length - 1);  //Пересобираю слово без знака пунктуации в конце
            } else{
                isPunctuation = false;
            }
        }

        //Метод для восстановления пунктуации
        public char[] addPunctuation(char[] array) {
                if (array != null){
                    if (isPunctuation){
                        array = Arrays.copyOf(array, array.length + 1);
                        array[array.length - 1] = punctSymbol;
                    }
                }else {
                    System.out.println("Массив не задан");
                }
            return array;
        }

       @Override
        public String toString() {
            return String.valueOf(wordChars);
        }
    }

}
