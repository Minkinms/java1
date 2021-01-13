package ru.progwards.java1.lessons.maps;
/*Информация по продажам
Во входном файле находятся данные в CSV формате, CSV - Comma Separated Values, значения разделенные запятыми. Каждая строка - данные об одной покупке. Входные данные
ФИ покупателя, наименование товара, количество, сумма
String, String, int, double*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SalesInfo {
    public static void main(String[] args) {




        String fileName = "C:\\Java\\Progwards\\HW 11.01.2021 задания до 15\\src\\ru\\progwards\\java1\\lessons\\maps\\SalesInfo.txt";

        SalesInfo salesInfo = new SalesInfo();
        System.out.println("Загружено строк: " + salesInfo.loadOrders(fileName));
//        System.out.println(salesInfo.getGoods());
        System.out.println(salesInfo.getCustomers());
    }

    //Переменные класса
    private ArrayList<String> salesLines = new ArrayList();


    //3.1 Вернуть количество успешно загруженных строк.
    //Если в строке более или менее 4-x полей, или количество и сумма не преобразуются в числа -
    //эту строку не загружаем.
    public int loadOrders(String fileName){
        int lineCount = 0;
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String saleLine = scanner.nextLine();
                if(checkSalesLine(saleLine)){
                    lineCount++;
                    salesLines.add(saleLine);
                }
//                System.out.println(saleLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lineCount;
    }

    //Метод для проверки строк перед загрузкой из файла
    public boolean checkSalesLine(String line){
        boolean result = false;
        String[] words = line.split(",");
        if(words.length == 4 && checkDigits(words[2].trim()) && checkDigits(words[3].trim())){
            result = true;
        }else {
            result =  false;
        }
        return result;
    }

    //Метод для проверки цифр количества и суммы
    public boolean checkDigits(String number){
        boolean result = false;
        for (int i = 0; i < number.length(); i++){
            if(Character.isDigit(number.charAt(i)) || number.charAt(i) == 46){    //Будут ли цифры с "." в double?
                result = true;
            }else{
                result = false;
                break;
            }
        }
        return result;
    }

    //3.2 Вернуть список товаров, отсортированный по наименованию товара.
    // В String - наименование товара, в Double - общая сумма продаж по товару
    public Map<String, Double> getGoods(){
        Map<String, Double> listProductsSum = new TreeMap<>();
        for (String sLine:salesLines){
            String[] sWords = sLine.split(",");
            String product = sWords[1].trim();
            double sum = Double.parseDouble(sWords[3]);
            if(listProductsSum.containsKey(product)){
                listProductsSum.put(product, listProductsSum.get(product) + sum);
            }else{
                listProductsSum.put(product, sum);
            }
        }
        return listProductsSum;
    }

    //3.3 Вернуть список покупателей, отсортированный по алфавиту.
    // В String  - ФИ, в Double - сумма всех покупок покупателя, в Integer - количество покупок
    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> listCustomers = new TreeMap<>();

        for (String sLine:salesLines){
            String[] sWords = sLine.split(",");
            String customer = sWords[0].trim();
            String product = sWords[1].trim();
            Integer quantity = Integer.parseInt(sWords[2].trim());
            double sum = Double.parseDouble(sWords[3]);

            if(!listCustomers.containsKey(product)){
                Double customerSum = getCustomerSum(customer);
                Integer customerQuantity = getCustomerQuantity(customer);
                AbstractMap.SimpleEntry<Double, Integer> customersSumAndQuantity
                        = new AbstractMap.SimpleEntry(customerSum, customerQuantity);
                listCustomers.put(customer, customersSumAndQuantity);
            }

        }
        return listCustomers;
    }

    public Double getCustomerSum(String customer){
        double result = 0.0;
        for (String sLine:salesLines) {
            String[] sWords = sLine.split(",");
            String customer1 = sWords[0].trim();
            double sum = Double.parseDouble(sWords[3]);

            if(customer.equals(customer1)){
                result = result + sum;
            }

        }
        return result;
    }

    public Integer getCustomerQuantity(String customer){
        int result = 0;
        for (String sLine:salesLines) {
            String[] sWords = sLine.split(",");
            String customer1 = sWords[0].trim();
            Integer quantity = Integer.parseInt(sWords[2].trim());

            if(customer.equals(customer1)){
                result = result + quantity;
            }

        }
        return result;
    }
}
