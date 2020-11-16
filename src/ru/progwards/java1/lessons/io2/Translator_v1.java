package ru.progwards.java1.lessons.io2;

import java.util.Arrays;

public class Translator_v1 {
    public static void main(String[] args) {
        String[] eng = {"hello", "world", "no", "i", "eat", "yes", "do", "it", "yourself" }; //9 слов
        String[] rus = {"привет", "мир", "нет", "я", "ем", "да", "делай", "это", "сам"};   //9 слов
        String var = null;
/*        String text = "Hello!";
//        String s = text.toUpperCase();
        System.out.println(text.toUpperCase());
//        text = s.toLowerCase();
        System.out.println(text.toLowerCase());*/

//        Translator translator = new Translator(eng, rus);   //Перевод с eng на rus
//        System.out.println(translator.translate("Hello world!"));
//        System.out.println(translator.translate("No, I eat."));
//        System.out.println(translator.translate("Yes, do it Yourself."));
//        System.out.println(translator.translate(var));

    }

    //Переменные класса
    private String[] inLang;
    private String[] outLang;

    //Конструктор класса
    public Translator_v1(String[] inLang, String[] outLang){
        this.inLang = Arrays.copyOf(inLang, inLang.length);
        this.outLang = Arrays.copyOf(outLang, outLang.length);
    }

    //Переводик
    public String translate(String sentence){
        StringBuilder tranSent = new StringBuilder();   //Переведенное предложение
        boolean symbolIsUpperCase = false;  //Хранение сведений о регистре заглавной буквы
        boolean punct = false;
        String punctSymbol = "";                       //Хранение сведений о пунктуации

        if (sentence != null) { //Проверка
            String[] arrayFromSentence = sentence.split(" ");     //Массив слов из предложения, разделитель пробел

            for (String word: arrayFromSentence){       //Перебор слов
                StringBuilder strBildWord = new StringBuilder(); //Переменная для сбора слова
                char[] charsFromWord = word.toCharArray();      //Разбиваю слово на массив символов

                //Определение регистра первой буквы слова
                //Запоминаю true, если буква, не цифра и в верхнем регистре
                symbolIsUpperCase = Character.isAlphabetic(charsFromWord[0])    //ситнтаксис по рекомендации Idea
                        && !Character.isDigit(charsFromWord[0])
                        && Character.isUpperCase(charsFromWord[0]);

                if (symbolIsUpperCase){                          //Перевожу в заглавную букву в нижний регистр
                    charsFromWord[0] = Character.toLowerCase(charsFromWord[0]);
                    //System.out.println(charsFromWord[0]);
                }

                //Определение наличия пунктуации
                //Условие: Последний символ не буква и не цифра
                if (!Character.isAlphabetic(charsFromWord[charsFromWord.length - 1]) &&
                        !Character.isDigit(charsFromWord[charsFromWord.length - 1])){
                    punct = true;
                    punctSymbol = String.valueOf(charsFromWord[charsFromWord.length - 1]); //Сохраняю символ

                    //Собираю слово без знака пунктуации в конце
                    strBildWord.append(charsFromWord, 0, charsFromWord.length - 1);

                } else{
                    punct = false;
                    punctSymbol = "";
                    strBildWord.append(charsFromWord, 0, charsFromWord.length);
                }

                //Поиск слова в словаре и его замена
                for (int i = 0; i < inLang.length; i++){
                    if (strBildWord.toString().equals(inLang[i])){
                        //System.out.println("Нашлось в словаре");
                        charsFromWord = outLang[i].toCharArray();
                        if (symbolIsUpperCase){
                            charsFromWord[0] = Character.toUpperCase(charsFromWord[0]);
                        }
                        tranSent.append(charsFromWord, 0, charsFromWord.length);
                        if (punct){
                            tranSent.append(punctSymbol);
                        }
                        if (!word.equals(arrayFromSentence[arrayFromSentence.length - 1])){
                            tranSent.append(" ");
                        }
                        break;
                    }
                }
            }

        } else {
            tranSent.append("Ошибка ввода!");
        }

        return tranSent.toString();
    }
}
