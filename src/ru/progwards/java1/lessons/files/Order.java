package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order{

    //переменные класса
    public String shopId;   //идентификатор магазина
    public String orderId;  //идентификатор заказа
    public String customerId;   //идентификатор покупателя
    public LocalDateTime datetime;  //дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items = new ArrayList<>();   //список позиций в заказе, отсортированный по наименованию товара
    public double sum;  //сумма стоимости всех позиций в заказе

    public boolean fileHasNoWrong = false;

    //Конструктор класса
/*    public Order(Path orderPath){
        getFromFileName(orderPath);
        getFromFileAttributes(orderPath);
        getItemsFromFile(orderPath);
        getSum();
    }*/

    public void getFromFileName(Path path) {
        File orderFileInfo = new File(path.toString());
        String[] orderFileName = orderFileInfo.getName().split("-");
        this.shopId = orderFileName[0].trim();
        this.orderId = orderFileName[1].trim();
        this.customerId = orderFileName[2].trim();
    }

    public void getFromFileAttributes(Path path) {
        try {
//            System.out.println(Files.getAttribute(path, "lastModifiedTime").toString());
            FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");
            Instant instant = fileTime.toInstant();
            this.datetime = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Moscow"));
//            this.datetime = LocalDateTime.parse(Files.getAttribute(path, "lastModifiedTime").toString(),
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"));   //Формат ввел вручную
                                                                                        //Были проблемы. Менялось кол-во знаков микросекунд
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }


    public void getItemsFromFile(Path path){
        try {
            List<String> itemsLines = new ArrayList<>(Files.readAllLines(path));
            for (String itemLine:itemsLines){
                if(checkItem(itemLine)){
//                    items.add(new OrderItem(itemLine));
                    items.add(createOrderItem(itemLine));
                    fileHasNoWrong = true;
                }else{
                    fileHasNoWrong = false;
                    break;
                }
            }
//            System.out.println(items);
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    public OrderItem createOrderItem(String itemLine) {
        OrderItem orderItem = new OrderItem();
        String[] itemInfoArray = itemLine.split(",");
        orderItem.googsName = itemInfoArray[0];
        orderItem.count = Integer.parseInt(itemInfoArray[1].trim());
        orderItem.price = Double.parseDouble(itemInfoArray[2].trim());
        return orderItem;
    }

    //Проверка содержимого перед формированием заказа
    public boolean checkItem(String itemLine){
        String[] itemInfoArray = itemLine.split(",");
        return itemInfoArray.length == 3 && isDigit(itemInfoArray[1].trim()) && isDigit(itemInfoArray[2].trim());

    }

    //Проверка формата данных в заказе
    public boolean isDigit(String str){
        char[] chars = str.toCharArray();
        for (char ch : chars){
            if(Character.isDigit(ch)){
                return true;
            }else{
               break;
            }
        }
        return false;
    }

    //Расчет суммы стоимости всех позиций в заказе
    public void getSum(){
        for (OrderItem orderItem : items){
            sum += (orderItem.count * orderItem.price);
        }
    }

    @Override
    public String toString() {
        return  "shopId:" + shopId +
                "; orderId:" + orderId +
                "; customerId:" + customerId +
                " сумма:" + sum +
                "; последнее обновление: " + datetime.toString();

    }
//
//    @Override
//    public int compareTo(Order o) {
//        return this.datetime.compareTo(o.datetime);
//    }
}
