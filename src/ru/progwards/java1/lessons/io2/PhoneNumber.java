package ru.progwards.java1.lessons.io2;

import java.io.IOException;
import java.util.Arrays;

public class PhoneNumber {
    public static void main(String[] args) throws IOException {
        String phone_1 = "+8(911)123-45-67";
        String phone_2 = "+89111234567";
        String phone_3 = "80123456789";
        String phone_4 = "91111234567";
        String phone_5 = "+++691";
        System.out.println(format(phone_1));
        System.out.println(format(phone_2));
        System.out.println(format(phone_3));
        System.out.println(format(phone_4));
        //System.out.println(format(phone_5));

    }

    //Метод для форматирования телефонных номеров
    public static String format(String phone) throws IOException {
        String normPhone = "";  //Вспомогательная переменная. Отформатированный номер
        StringBuilder stringBuilder = new StringBuilder();

        for (char num : phone.toCharArray()){       //Выделяю в stringBuilder только цифры
            if(Character.isDigit(num)){
                stringBuilder.append((num));
            }
        }

        if (!((stringBuilder.length() == 10) || (stringBuilder.length() == 11))){   //Проверяю количество
            throw new IOException("Неверный формат номера");    //Допустимое кол-во цифр 10 или 11, в противном случае кидаю исключение
        }
        if (stringBuilder.length() == 11) {     //если цифр 11, убираю первую. Предполагаю, что это 8 или опечатка (введена случайная лишняя цифра)
            if (stringBuilder.charAt(0) == 56){ //Проверяю не стоит ли 8 в начале.
                stringBuilder.deleteCharAt(0);  //Если да, то отсекаю её, чтобы заменить на +7
            } else stringBuilder.deleteCharAt(10);  //Если нет, то возможно вводился код и корректнее отсечь последнюю

        }

        //перехожу от stringBuilder к строке и перевожу её в массив символов
        normPhone = stringBuilder.toString();
        String[] arrayPhone = normPhone.split("");

        //Форматирование под выходной шаблон
        normPhone = "+7" +
                    "(" + arrayPhone[0] + arrayPhone[1] + arrayPhone[2] + ")" +           //Код (ххх)
                    arrayPhone[3] + arrayPhone[4] + arrayPhone[5] +                       //Часть номера до "-"
                    "-" + arrayPhone[6] + arrayPhone[7] + arrayPhone[8] +arrayPhone[9];   //Часть номера после "-"

        return normPhone;
    }

}
