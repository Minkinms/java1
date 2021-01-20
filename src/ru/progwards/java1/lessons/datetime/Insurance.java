package ru.progwards.java1.lessons.datetime;
/* Класс должен проверять валидность страховок, например для выезжающих за рубеж.
Каждая страховка имеет дату-время начала, и продолжительность.*/


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Insurance {
    public static void main(String[] args) {
//        String str = "01.01.2020 16:00:00.000 +0300 Moscow Standard Time";
        String str = "2020-01-01T16:00+03:00[Europe/Moscow]"; //2020-01-01T16:00+03:00[Europe/Moscow]
        Locale locale = Locale.US;
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(locale);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zdt_test1 = ZonedDateTime.parse("2020-01-01T16:00+03:00[Europe/Moscow]", dateTimeFormatter);

//        ZonedDateTime zdt_test3 = ZonedDateTime.parse("2020-12-01T16:00+03:00[Europe/Moscow]", dateTimeFormatter);
//        ZonedDateTime zdt_test4 = ZonedDateTime.parse("2021-11-01T16:00+03:00[Europe/Moscow]", dateTimeFormatter);
//        ZonedDateTime zdt_test5 = zdt_test3.plusDays(366);
//        System.out.println(zdt_test3.compareTo(zdt_test4));
//        System.out.println(zdt_test1);
//        System.out.println(zdt_test2);
//        System.out.println(zdt_test3 + "\n");

//        Duration duration1 = Duration.ofDays(32);   //Установка продолжительности через дни
////        System.out.println(duration1.toString());   //PT1440H
//        //Использование первого конструктора
//        Insurance ins_1 = new Insurance(zdt_test1);
////        ins_1.setDuration(duration1);               //Продолжительность 1
//        ZonedDateTime zdt_test6 = ZonedDateTime.parse("2020-02-01T16:00+03:00[Europe/Moscow]", dateTimeFormatter);
////        System.out.println(ins_1.checkValid(zdt_test6));
//        System.out.println(ins_1.toString());
//        //Использование второго конструктора
//        String str2 = "2020-09-01";
//        Insurance ins_2 = new Insurance(str2, FormatStyle.SHORT);
//        ZonedDateTime zdt_test2 = ZonedDateTime.parse("2021-09-01T00:00+03:00[Europe/Moscow]", dateTimeFormatter);
//        ins_2.setDuration(zdt_test2);
//        System.out.println(ins_2.toString());
//
//        String str3 = "2020-10-01T02:00";
//        Insurance ins_3 = new Insurance(str3, FormatStyle.LONG);
//        ins_3.setDuration( 6, 1, 1);
//        System.out.println(ins_3.toString());
//
//        String str4 = "2020-05-01T16:00+03:00[Europe/Moscow]";
//        Insurance ins_4 = new Insurance(str4, FormatStyle.FULL);
//        ins_4.setDuration("2592000000", FormatStyle.SHORT); //Продолжительность - месяц
//        System.out.println(ins_4.toString());

        String str5 = "2020-12-20T00:00";
        Insurance ins_5 = new Insurance(str5, FormatStyle.LONG);
        ins_5.setDuration("0000-01-04T06:10", FormatStyle.LONG); //Продолжительность - 2 месяца
        System.out.println(ins_5.toString());

//        String str6 = "2021-01-01T00:00";
//        Insurance ins_6 = new Insurance(str6, FormatStyle.LONG);
//        ins_6.setDuration("PT1440H", FormatStyle.FULL); //Продолжительность - 2 месяца
//        System.out.println(ins_6.toString());

    }

//  1.1  Реализовать локальный public static enum FormatStyle {SHORT, LONG, FULL} - стиль формата даты-времени
    public static enum FormatStyle {SHORT, LONG, FULL}

//  1.2. Реализовать приватные свойства класса:
    private ZonedDateTime start; //- дата-время начала действия страховки.
    private Duration duration; //- продолжительность действия.



//  1.2 Реализовать конструкторы:
//  - установить дату-время начала действия страховки.
    public Insurance(ZonedDateTime start){
        this.start = start;
    }

//  - установить дату-время начала действия страховки
/*    SHORT соответствует ISO_LOCAL_DATE
    LONG  - ISO_LOCAL_DATE_TIME
    FULL - ISO_ZONED_DATE_TIME
    Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.*/
    public Insurance(String strStart, FormatStyle style){
//        DateTimeFormatter dtf;          //Возможно, что устанавливать формат не нужно. этот по умолчанию
        switch (style){
            case SHORT:
//                dtf = DateTimeFormatter.ISO_LOCAL_DATE;
                LocalDate localDate = LocalDate.parse(strStart);
                this.start = ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneId.of("Europe/Moscow"));   //LocalTime.MIDNIGHT пришлось принять
                break;
            case LONG:
//                dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime localDateTime = LocalDateTime.parse(strStart);
                this.start = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Moscow"));
                break;
            case FULL:
//                dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;
                this.start = ZonedDateTime.parse(strStart);
                break;
        }
    }

//  1.3 Реализовать методы, устанавливающие продолжительность действия страховки:
//  - установить продолжительность действия страховки.
    public void setDuration(Duration duration){
        this.duration = duration;
    }

//  - установить продолжительность действия страховки, задав дату-время окончания.
    public void setDuration(ZonedDateTime expiration){
        this.duration = Duration.between(start, expiration);
    }

//  - установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов.
    public void setDuration(int months, int days, int hours){
        this.duration = Duration.between(start, start.plusMonths(months).plusDays(days).plusHours(hours));
    }

//  - установить продолжительность действия страховки
  /*SHORT - целое число миллисекунд (тип long)
    LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает,
            что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
            FULL - стандартный формат Duration, который получается через toString()*/
    //PT1440H

    public void setDuration(String strDuration, FormatStyle style){
        switch (style){
            case SHORT:
                this.duration = Duration.ofMillis(Long.parseLong(strDuration));
                break;
            case LONG:
                LocalDateTime localDateTime = LocalDateTime.parse(strDuration);  //ISO_LOCAL_DATE_TIME;

//                System.out.println(localDateTime.getHour());
                this.duration = Duration.between(start, start.plusYears(localDateTime.getYear())
                                                        .plusMonths(localDateTime.getMonthValue())
                                                        .plusDays(localDateTime.getDayOfYear())
                                                        .plusHours(localDateTime.getHour()));
//                this.duration = Duration.between(start, start.plusMonths(localDateTime.getMonthValue()));
//                System.out.println(duration.toDays());
//                this.duration = Duration.between(start, start.plusMonths(localDateTime.getMonthValue()).plusDays(localDateTime.getDayOfYear()));
////                                                        .plusYears(localDateTime.getYear())
////                                                        .plusHours(localDateTime.getHour()));
//                System.out.println(duration.toDays());


//                System.out.println(localDateTime.getMinute());
//                System.out.println(localDateTime.getDayOfYear());
//                System.out.println(localDateTime.getMonthValue());
//                System.out.println(start.plusMonths(localDateTime.getMonthValue())); //"2020-12-20T00:00"
//                System.out.println(start.plusMonths(localDateTime.getMonthValue()).plusDays(localDateTime.getDayOfYear()));
                //"2020-12-20T00:00"
//                System.out.println(duration.toDays());
//                System.out.println(start.plusMonths(localDateTime.getMonthValue()).plusYears(localDateTime.getYear()).plusHours(localDateTime.getHour()));
                break;
            case FULL:
                this.duration = Duration.parse(strDuration);
                break;
        }
    }

//  1.4 Реализовать методы возврата информации:
//  - проверить действительна ли страховка на указанную дату-время.
// Если продолжительность не задана считать страховку бессрочной.
    public boolean checkValid(ZonedDateTime dateTime) {
        if(dateTime.compareTo(this.start) >=0){
            if(this.duration != null){
                if((dateTime.compareTo(this.start.plusDays(duration.toDays()) )) < 0){
                    return true;
                }else return false;
            }else return true;

        }else return false;
    }

//  - вернуть строку формата "Insurance issued on " + start + validStr,
//  где validStr = " is valid", если страховка действительна на данный момент и
//  " is not valid", если она недействительна.
    public String toString() {
        String validStr;
        ZonedDateTime thisDay = Instant.now().atZone(ZoneId.of("Europe/Moscow"));
        if(checkValid(thisDay)){
            validStr = " is valid";
        }else validStr = " is not valid";
        return "Insurance issued on " + this.start + validStr;
//        return "Insurance issued on " + this.start + " Продолжительность действия " + this.duration.toDays();
    }


}
