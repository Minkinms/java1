package ru.progwards.java1.lessons.files;
/*Информация о заказах поступает в виде файлов,
которые лежат в под-папках разбитых по неделям,
имена папок не имеют значения.
Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv
где AAA - обязательные 3 символа shopId - идентификатор магазина,
999999 - обязательные 6 символов orderId - номер заказа,
ZZZZ - обязательные 4 символа customerId - идентификатор покупателя,
расширение файла - csv,
например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”

Содержимое каждого файла имеет формат CSV (Comma Separated Values) со следующими данными
Наименование товара, количество, цена за единицу
*/

//import ru.progwards.java1.lessons.queues.Order;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class OrderProcessor {
    public static void main(String[] args) throws IOException {
        String startPath = "C:\\Minkin_Orders";
        OrderProcessor orderProcessor = new OrderProcessor(startPath);
//        LocalDate start = LocalDate.of(2021, 2, 13);
//        LocalDate finish = LocalDate.of(2021, 2, 12);
//        String shopId = "S02";
        LocalDate start =  null;
        LocalDate finish = null;
        String shopId = null;
        System.out.println("Количество файлов с ошибками " + orderProcessor.loadOrders(start, finish, shopId));
//        System.out.println("Количество файлов с ошибками " + orderProcessor.loadOrders(start, finish, shopId));
        for (Order ord : orderProcessor.orderList) {
            System.out.println("\n" + ord);
            for (OrderItem oi : ord.items) {
                System.out.println(oi);
            }
        }
//---------------------------------------------
        System.out.println("\n Метод process \n");
        shopId = null;
        for (Order ord : orderProcessor.process(shopId)){
            System.out.println("\n" + ord);
            for (OrderItem oi : ord.items){
                System.out.println(oi);
            }
        }
//---------------------------------------------
        System.out.println("\n Метод statisticsByShop \n");
/*        for (var entry : orderProcessor.statisticsByShop().entrySet()){    //Map<String (shopID), Double>
            System.out.println("shopID: " + entry.getKey() + " Сумма " + entry.getValue() );
        }*/
//---------------------------------------------
        System.out.println("\n Метод statisticsByGoods \n");
/*        for (var entry : orderProcessor.statisticsByGoods().entrySet()){    //Map<String (shopID), Double>
            System.out.println("Товар:  " + entry.getKey() + "; Сумма: " + entry.getValue() );
        }*/
//---------------------------------------------
        System.out.println("\n Метод statisticsByDay \n");
        for (var entry : orderProcessor.statisticsByDay().entrySet()){    //Map<String (shopID), Double>
            System.out.println("День:  " + entry.getKey() + "; Сумма: " + entry.getValue() );
        }


    }

    //-------------------------------------------------------------------------------------------------------
    //Переменные класса
    Path startPath;
    List<Order> orderList = new ArrayList<>();      //Список заказов
    int countWrongOrders = 0;                       //Количество неверных заказов (файлов заказов с ошибками)


    //3.3 конструктор - инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.startPath = Path.of(startPath);
    }


    //    3.4 метод  - загружает заказы за указанный диапазон дат,
//    с start до finish, обе даты включительно.
/*    Если start == null, значит нет ограничения по дате слева,
    если finish == null, значит нет ограничения по дате справа,
    если shopId == null - грузим для всех магазинов.
    При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется,
    т.е. Не поступает в обработку.
    Метод возвращает количество файлов с ошибками.
    При этом, если в классе содержалась информация, ее надо удалить*/
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        this.countWrongOrders = 0;      //Обнуление на случай повторных выховов метода
        orderList.clear();

        List<Path> ordersPath = new ArrayList<>(findFiles1());  //Список файлов с расширением .csv
        //полученный список пересохраняю исключая пути не подходящие по условиям
        ordersPath = new ArrayList<>(checkDateConditions(ordersPath, start, finish));   //Проверка условий. Диапазон дат
        ordersPath = new ArrayList<>(checkFormatConditions(ordersPath));                //Проверка условий. Формат
        ordersPath = new ArrayList<>(checkShopIdConditions(ordersPath, shopId));        //Проверка условий. Магазин

        for(Path path:ordersPath){          //Подготовка списка заказов с учетом условий отбора
            Order order = new Order();
            createOrder(order, path);       //Создание заказа без использования конструктора класса Order
            if(order.fileHasNoWrong) {
                orderList.add(order);
            }else {
             countWrongOrders++;     //Количество файлов с ошибками
            }
        }
        return this.countWrongOrders;
    }

    //Метод для отбора файлов с подходящим расширением
    private List<Path> findFiles1() {
        List<Path> orderFilesList = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv"); //Отбор файлов с нужным расширением
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {      //Файлы соответствующие шаблону
                        orderFilesList.add(path);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return orderFilesList;
    }

    //Проверка условий отбора файлов по датам
    public List<Path> checkDateConditions(List<Path> ordersPath, LocalDate start, LocalDate finish) {
        List<Path> result = new ArrayList<>();
        LocalDateTime fileDateTime = null;
        //перебираю пути. Файлы по путям проверяю на соответствие диапазону дат
        for (Path path : ordersPath) {
            try {
                FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");    //Получение даты файла из аттрибута
                fileDateTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.of("Europe/Moscow"));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

            if(start != null && finish !=null && fileDateTime != null){
                if(fileDateTime.compareTo(LocalDateTime.of(start, LocalTime.MIDNIGHT)) >= 0
                    && fileDateTime.compareTo(LocalDateTime.of(finish, LocalTime.MAX)) <= 0) {
                    result.add(path);
                }
            }else {
                if(start != null &&  fileDateTime != null){
                    if(fileDateTime.compareTo(LocalDateTime.of(start, LocalTime.MIDNIGHT)) >= 0) {
                        result.add(path);
                    }
                }else {
                    if(finish != null &&  fileDateTime != null){
                        if(fileDateTime.compareTo(LocalDateTime.of(finish, LocalTime.MAX)) <= 0) {
                            result.add(path);
                        }
                    }else {
                        if(fileDateTime != null) {
                            result.add(path);
                        }
                    }
                }
            }
        }
        return result;
    }

    //Проверка форматов файлов
    public List<Path> checkFormatConditions(List<Path> ordersPath){
        List<Path> result = new ArrayList<>();
        //Повторно проверяю, но уже на полное соответствие формату
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???[-]??????[-]????.csv");
        for(Path path : ordersPath){
            if(pathMatcher.matches(path)){
                result.add(path);
            }//else {
               // countWrongOrders++;     //Количество файлов с ошибкми в именах
            //}
        }
        return result;
    }

    //проверка условия отбора файлов по ShopID
    public List<Path> checkShopIdConditions(List<Path> ordersPath, String shopId){
        List<Path> result = new ArrayList<>();
        for (Path path : ordersPath){
            File orderFileInfo = new File(path.toString());
            String[] orderFileName = orderFileInfo.getName().split("-");
            String orderShopId = orderFileName[0].trim();
            if (shopId != null) {
                if (shopId.equals(orderShopId)) {
                    result.add(path);
                }
            }else {
                result.add(path);
            }
        }

        return result;
    }

    //Создание заказа без использования конструктора класса Order
    public void createOrder(Order order, Path orderPath) {
        order.getFromFileName(orderPath);
        order.getFromFileAttributes(orderPath);
        order.getItemsFromFile(orderPath);
        order.getSum();
    }

//    3.5 метод  - выдать список заказов в порядке обработки
//    (отсортированные по дате-времени), для заданного магазина.
//    Если shopId == null, то для всех
    public List<Order> process(String shopId) {
        List<Order> ordersProcess = new ArrayList<>();
        for (Order order : orderList) {
            if(shopId != null) {
                if (order.shopId.equals(shopId)) {
                    ordersProcess.add(order);
                }
            }else ordersProcess.add(order);
        }
        ordersProcess.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        });
        return ordersProcess;
    }

//    3.6 метод  - выдать информацию по объему продаж по магазинам
//    (отсортированную по ключам):
//    String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
    public Map<String, Double> statisticsByShop() {
        TreeMap<String, Double> resultMap = new TreeMap<>();    //Использую TreeMap для автосортировки
        for (Order order : orderList) {
            if (resultMap.containsKey(order.shopId)) {                      //Поиск результата по ключу
                double shopSum = resultMap.get(order.shopId) + order.sum;   //Добавление суммы к имеющейся, если таковая запись уже есть
                resultMap.put(order.shopId, shopSum);
            } else {
                resultMap.put(order.shopId, order.sum);
            }
        }
        return resultMap;
    }

//  3.7 метод  - выдать информацию по объему продаж по товарам
//  (отсортированную по ключам):
//  String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
    public Map<String, Double> statisticsByGoods() {
        TreeMap<String, Double> resultMap = new TreeMap<>();
        for (Order order : orderList) {
            for (OrderItem orderItem : order.items) {
                if (resultMap.containsKey(orderItem.googsName)) {
                    double goodsSum = resultMap.get(orderItem.googsName) + (orderItem.price * orderItem.count);
                    resultMap.put(orderItem.googsName, goodsSum);
                } else {
                    resultMap.put(orderItem.googsName, orderItem.price * orderItem.count);
                }
            }
        }
        return resultMap;
    }

    //  3.8 метод  - выдать информацию по объему продаж по дням
//  (отсортированную по ключам):
//  LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
    public Map<LocalDate, Double> statisticsByDay() {
        TreeMap<LocalDate, Double> resultMap = new TreeMap<>();
        for (Order order : orderList) {
            if (resultMap.containsKey(order.datetime.toLocalDate())) {
                double daySum = resultMap.get(order.datetime.toLocalDate()) + order.sum;
                resultMap.put(order.datetime.toLocalDate(), daySum);
            } else {
                resultMap.put(order.datetime.toLocalDate(), order.sum);
            }
        }
        return resultMap;
    }


}
