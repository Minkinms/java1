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
/*        Path path = Path.of("C:\\Minkin_Orders\\S01-A001Z1-0001.csv");
        FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");
        Instant instant = fileTime.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Moscow"));
        System.out.println(ldt);
        System.out.println(Files.getAttribute(path, "lastModifiedTime").getClass());*/


        String startPath = "C:\\Minkin_Orders";
        OrderProcessor orderProcessor = new OrderProcessor(startPath);
//        LocalDate start = LocalDate.of(2021, 2, 11);
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
        shopId = "S02";
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
/*        for (var entry : orderProcessor.statisticsByDay().entrySet()){    //Map<String (shopID), Double>
            System.out.println("День:  " + entry.getKey() + "; Сумма: " + entry.getValue() );
        }*/


    }

    //-------------------------------------------------------------------------------------------------------
    //Переменные класса
    Path startPath;
    List<Order> orderList = new ArrayList<>();      //Список заказов
    int countWrongOrders = 0;                        //Количество неверных заказов (файлов заказов с ошибками)


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
        this.countWrongOrders = 0;
        orderList.clear();
        List<Order> orderAllList = new ArrayList<>();      //Список заказов полный
        //Было
/*        List<Path> ordersPath = new ArrayList<>(findFiles());   //Список путей с правильными именами файлов. Запись кол-ва файлов  ошибками в именах

        for(Path path:ordersPath){
//            System.out.println(path);
//            Order order = new Order(path);
            Order order = new Order();
            createOrder(order, path);       //Создание заказа без использования конструктора класса Order
//            if(order.fileHasNoWrong){
                orderAllList.add(order);    //Общий список. До сортировки
//            }else {
//                countWrongOrders++;
//            }
        }

        getOrderList(orderAllList, start, finish, shopId);    //Метод для формирования списка по условиям*/

        List<Path> ordersPath = new ArrayList<>(findFiles1());  //Список файлов с расширением .csv
        ordersPath = new ArrayList<>(checkDateConditions(ordersPath, start, finish));
        ordersPath = new ArrayList<>(checkFormatConditions(ordersPath));
        ordersPath = new ArrayList<>(checkShopIdConditions(ordersPath, shopId));

        for(Path path:ordersPath){
            Order order = new Order();
            createOrder(order, path);       //Создание заказа без использования конструктора класса Order
            if(order.fileHasNoWrong) {
                orderList.add(order);
            }
        }


        return this.countWrongOrders;
    }

    //Метод для отбора файлов с подходящим форматов имени
    private List<Path> findFiles1() {
//        TreeMap<LocalDateTime, Path> orderFilesMap = new TreeMap<>();
        List<Path> orderFilesList = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {      //Файлы соответствующие шаблону
//                    System.out.println(path);
//                        try {
//                            FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");    //Получение даты файла из аттрибута
//                            Instant instant = fileTime.toInstant();
//                            LocalDateTime fileDate = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Moscow"));
                        orderFilesList.add(path);
//                        }catch (IOException exception){
//                            System.out.println(exception.getMessage());
//                        }

                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
//                    System.out.println("Ошибка в названии пути");
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

//        checkConditions(orderFilesMap, start, finish);
//        List<Path> orderFilesList = new ArrayList<>(orderFilesMap.values());
//        List<Path> orderFilesList = new ArrayList<>(checkConditions(orderFilesMap, start, finish).values());
        return orderFilesList;
    }

    //Проверка условий отбора файлов по датам
    public List<Path> checkDateConditions(List<Path> ordersPath, LocalDate start, LocalDate finish) {   //TODO:Сделать проверку только по датам
        List<Path> result = new ArrayList<>();
        LocalDateTime fileDateTime = null;
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
                        if(fileDateTime.compareTo(LocalDateTime.of(finish, LocalTime.MAX)) >= 0) {
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

/*        if(start != null){
            LocalDateTime startLDT = LocalDateTime.of(start, LocalTime.MIDNIGHT);
            orderFilesMap = new TreeMap<>(orderFilesMap.headMap(startLDT));
        }
        if(finish != null){
            LocalDateTime startLDT = LocalDateTime.of(finish, LocalTime.MIDNIGHT);
            orderFilesMap = new TreeMap<>(orderFilesMap.tailMap(startLDT));
        }*/
        return result;
    }

    //Проверка форматов файлов
    public List<Path> checkFormatConditions(List<Path> ordersPath){
        List<Path> result = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???[-]??????[-]????.csv");
        for(Path path : ordersPath){
            if(pathMatcher.matches(path)){
                result.add(path);
            }else {
//                System.out.println("Ошибка в названии файла");
                countWrongOrders++;     //Количество файлов с ошибкми в именах
            }
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


    //Метод для отбора файлов с подходящим форматов имени
    private List<Path> findFiles() {
        List<Path> orderFiles = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???[-]??????[-]????.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {


                    if (pathMatcher.matches(path)) {      //Файлы соответствующие шаблону
//                    System.out.println(path);
                        orderFiles.add(path);
                        //
                    } else {
//                        System.out.println("Ошибка в названии файла");
                        countWrongOrders++;     //Количество файлов с ошибкми в именах
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.out.println("Ошибка в названии пути");
                    return FileVisitResult.CONTINUE;
                }

            });

//            System.out.println("\nВсего неподходящих файлов " + countWrongOrders);

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return orderFiles;
    }


    //Создание заказа без использования конструктора класса Order
    public void createOrder(Order order, Path orderPath) {
        order.getFromFileName(orderPath);
        order.getFromFileAttributes(orderPath);
        order.getItemsFromFile(orderPath);
        order.getSum();
    }

    //Проверка условий отбора файлов с использованием Map
    public TreeMap<LocalDateTime, Path> checkConditions(TreeMap<LocalDateTime, Path> orderFilesMap, LocalDate start, LocalDate finish) {   //TODO:Сделать проверку только по датам
        if (start != null) {
            LocalDateTime startLDT = LocalDateTime.of(start, LocalTime.MIDNIGHT);
            orderFilesMap = new TreeMap<>(orderFilesMap.headMap(startLDT));
        }
        if (finish != null) {
            LocalDateTime startLDT = LocalDateTime.of(finish, LocalTime.MIDNIGHT);
            orderFilesMap = new TreeMap<>(orderFilesMap.tailMap(startLDT));
        }
        return orderFilesMap;
    }

    //Метод для формирования списка по условиям
/*    Если start == null, значит нет ограничения по дате слева,
    если finish == null, значит нет ограничения по дате справа,
    если shopId == null - грузим для всех магазинов.*/
    public void getOrderList(List<Order> orderAllList, LocalDate start, LocalDate finish, String shopId) {
        TreeMap<LocalDateTime, Order> orderMap = new TreeMap<>();
        for (Order order : orderAllList) {           //Создание Мар с ключем по дате
            orderMap.put(order.datetime, order);
        }

        if (start != null) {
            LocalDateTime startLDT = LocalDateTime.of(start, LocalTime.MIDNIGHT);
            orderMap = new TreeMap<>(orderMap.tailMap(startLDT));
        }
        if (finish != null) {
            LocalDateTime startLDT = LocalDateTime.of(finish, LocalTime.NOON);
            orderMap = new TreeMap<>(orderMap.headMap(startLDT));
        }
        if (shopId != null) {
            for (Order order : orderMap.values()) {
                if (order.shopId.equals(shopId)) {
                    orderList.add(orderMap.get(order.datetime));
                }
            }
        } else {
            for (Order order : orderMap.values()) {
                orderList.add(orderMap.get(order.datetime));
            }
        }

//        System.out.println("");
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
        ordersProcess.sort(new Comparator<Order>() {        //Хотя в TreeMap они уже отсортированы
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
        TreeMap<String, Double> resultMap = new TreeMap<>();
        for (Order order : orderList) {
            if (resultMap.containsKey(order.shopId)) {
                double shopSum = resultMap.get(order.shopId) + order.sum;
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
