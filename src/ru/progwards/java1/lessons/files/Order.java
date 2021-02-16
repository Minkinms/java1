package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Order{

    //переменные класса
    public String shopId;           //идентификатор магазина
    public String orderId;          //идентификатор заказа
    public String customerId;       //идентификатор покупателя
    public LocalDateTime datetime;  //дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items = new ArrayList<>();   //список позиций в заказе, отсортированный по наименованию товара
    public double sum;  //сумма стоимости всех позиций в заказе

    public boolean fileHasNoWrong = false;

    //Конструктор класса (С конструктором было лучше)
/*    public Order(Path orderPath){
        getFromFileName(orderPath);
        getFromFileAttributes(orderPath);
        getItemsFromFile(orderPath);
        getSum();
    }*/

    //Получение данных из имени файла
    public void getFromFileName(Path path) {
        File orderFileInfo = new File(path.toString());
        String[] orderFileName = orderFileInfo.getName().split("-");
        this.shopId = orderFileName[0].trim();
        this.orderId = orderFileName[1].trim();
        this.customerId = orderFileName[2].trim().substring(0, 4);

    }

    //Получение данных из аттрибута
    public void getFromFileAttributes(Path path) {
        try {
            FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");
            Instant instant = fileTime.toInstant();
            this.datetime = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Moscow"));
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    //Получение данных из содержимого файла
    public void getItemsFromFile(Path path){
        //Считываю и затем перебираю массив строк из файла
        try {
            List<String> itemsLines = new ArrayList<>(Files.readAllLines(path));
            for (String itemLine:itemsLines){
                if(checkItem(itemLine)){
                    items.add(createOrderItem(itemLine));   //Создаю товар в заказе
                    fileHasNoWrong = true;
                }else{
                    fileHasNoWrong = false;
                    break;
                }
            }
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    //Метод для создания заказа
    public OrderItem createOrderItem(String itemLine) {
        OrderItem orderItem = new OrderItem();
        String[] itemInfoArray = itemLine.split(",");
        orderItem.googsName = itemInfoArray[0];
        orderItem.count = Integer.parseInt(itemInfoArray[1].trim());
        orderItem.price = Double.parseDouble(itemInfoArray[2].trim());
        return orderItem;
    }

    //Проверка содержимого перед формированием заказа
    //три поля, разделенные ",". Первое поле  - содержание не важно, второе и третье должны быть цифры
    public boolean checkItem(String itemLine){
        String[] itemInfoArray = itemLine.split(",");
        return itemInfoArray.length == 3 && isDigit(itemInfoArray[1].trim()) && isDigit(itemInfoArray[2].trim());

    }

    //Проверка формата данных в заказе
    //Есть ли готовые методы проверки на цифру?
    public boolean isDigit(String str){
        char[] chars = str.toCharArray();
        for (char ch : chars){
            if(Character.isDigit(ch)){ //Возможно нужна проверка на "." для double
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
            this.sum += (orderItem.count * orderItem.price);
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

}
