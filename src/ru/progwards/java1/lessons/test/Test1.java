package ru.progwards.java1.lessons.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Test1 {
    public static void main(String[] args) {
//        System.out.print("Сделаю коммит, запушу в репо: робот, проверяй теперь всё это...");
        Test1 test1 = new Test1();
/*        String str = "01.01.2020 16:27:14.444 +0300 Moscow Standard Time";
//        String str = "01.01.2020";

        System.out.println(test1.parseZDT(str));*/
        System.out.println(test1.createFolder("Minkin"));

    }

/*    Напишите метод, с сигнатурой ZonedDateTime parseZDT(String str),
    который возвращает ZonedDateTime,
    парся строку вида "01.01.2020 16:27:14.444 +0300 Moscow Standard Time"*/

   public ZonedDateTime parseZDT (String str){
       Locale locale = Locale.US;
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(locale);
        ZonedDateTime zdt = ZonedDateTime.parse(str, dateTimeFormatter);
        return zdt;
    }

    String createFolder(String name){
        Path path = Paths.get("");
        path = Paths.get(path.toAbsolutePath().toString()).getParent();
//        System.out.println(path.toAbsolutePath().toString());

        return  path.toString();
    }

}
