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
        System.out.println(salesInfo.getGoods());
        System.out.println(salesInfo.getCustomers());
    }

    //Переменные класса
    private ArrayList<Sale> salesLines = new ArrayList();       //Список покупок (или продаж)


    //3.1 Вернуть количество успешно загруженных строк.
    //Если в строке более или менее 4-x полей, или количество и сумма не преобразуются в числа -
    //эту строку не загружаем.
    public int loadOrders(String fileName){
        int lineCount = 0;
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String saleLine = scanner.nextLine();
                if(checkSalesLine(saleLine)){           //проверка строк перед загрузкой из файла
                    lineCount++;
                    salesLines.add(getSale(saleLine));  //Наполнение списка покупок объектами Sale на основе строк
                }
//                System.out.println(saleLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lineCount;
    }

    //Метод для создания объекта "покупка"
    public Sale getSale(String line){
        String[] words = line.split(",");
        String customer = words[0].trim();
        String product = words[1].trim();
        int quantity = Integer.parseInt(words[2].trim());
        double sum = Double.parseDouble(words[3].trim());

        return new Sale(customer, product, quantity, sum);
    }

    //Метод для проверки строк перед загрузкой из файла
    public boolean checkSalesLine(String line){
        boolean result = false;
        String[] words = line.split(",");
        if(words.length == 4 && checkDigits(words[2].trim()) && checkDigits(words[3].trim())){
            result = true;
        }
        return result;
    }

    //Метод для проверки цифр
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
        Map<String, Double> listProductsSum = new TreeMap<>();      //Использую сортированный словарь
        for (Sale sale:salesLines){                                 //перебираю список покупок, если товар уже есть, складываю суммы и перезаписываю
            if(listProductsSum.containsKey(sale.getProduct())){
                listProductsSum.put(sale.getProduct(), listProductsSum.get(sale.getProduct()) + sale.getSum());
            }else{
                listProductsSum.put(sale.getProduct(), sale.getSum());
            }
        }
        return listProductsSum;
    }

    //3.3 Вернуть список покупателей, отсортированный по алфавиту.
    // В String  - ФИ, в Double - сумма всех покупок покупателя, в Integer - количество покупок
    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> listCustomers = new TreeMap<>();  //Использую сортированный словарь
        for (Sale sale:salesLines){
            if(!listCustomers.containsKey(sale.getProduct())){
                AbstractMap.SimpleEntry<Double, Integer> customersSumAndQuantity
                        = new AbstractMap.SimpleEntry(getCustomerSum(sale.getCustomer()),
                                                        getCustomerQuantity(sale.getCustomer()));
                listCustomers.put(sale.getCustomer(), customersSumAndQuantity);
            }
        }
        return listCustomers;
    }

    //Метод для подсчета суммы всех покупок покупателя
    public Double getCustomerSum(String customer){
        double result = 0.0;
        for (Sale sale:salesLines) {                    //перебираю список, ищу совпадения и складываю суммы
             if(customer.equals(sale.getCustomer())){
                result = result + sale.getSum();
            }
        }
        return result;
    }

    //Метод для подсчета количества всех покупок покупателя
    public Integer getCustomerQuantity(String customer){
        int result = 0;
        for (Sale sale:salesLines) {                    //перебираю список, ищу совпадения и складываю количества
            if(customer.equals(sale.getCustomer())){
                result = result + sale.getQuantity();
            }
        }
        return result;
    }

    //Класс, описывающий покупку
    public static class Sale{
        //Переменные класса
        private String customer;    //Покупатель
        private String product;     //Купленный продукт
        private int quantity;       //Количество купленного
        private double sum;         //Сумма покупки

        //Конструктор класса
        public Sale(String customer, String product, int quantity, double sum) {
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
            this.sum = sum;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }
    }
}
