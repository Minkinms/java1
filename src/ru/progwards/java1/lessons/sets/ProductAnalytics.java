package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    public static void main(String[] args) {
        String[] arrayFoodShop = {"Яблоко", "Хлеб", "Клейкая лента", "Пакет"};
        String[] arrayToolsShop = {"Гвозди", "Пакет", "Клейкая лента", "Молоток"};
        String[] arrayGoodsShop = {"Пакет", "Ведро", "Швабра", "Порошок"};
        String[] arrayNowhere = {"Мыло", "Маски"};

        //Общий список продуктов для магазинов
        List<Product> products = new ArrayList<>();
        for (String someGoods : arrayNowhere) {
            products.add(new Product(someGoods));
        }

        //Списки продуктов для магазинов
        List<Product> toFoodShop = new ArrayList<>();
        for (String food : arrayFoodShop) {
            toFoodShop.add(new Product(food));
            products.add(new Product(food));
        }
        List<Product> toToolsShop = new ArrayList<>();
        for (String tools : arrayToolsShop) {
            toToolsShop.add(new Product(tools));
            products.add(new Product(tools));
        }
        List<Product> toGoodsShop = new ArrayList<>();
        for (String goods : arrayGoodsShop) {
            toGoodsShop.add(new Product(goods));
            products.add(new Product(goods));
        }

        //Список магазинов
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(toFoodShop));
        shops.add(new Shop(toToolsShop));
        shops.add(new Shop(toGoodsShop));

        ProductAnalytics productAnalytics = new ProductAnalytics(products, shops);
        System.out.println("Товары имеющиеся во всех магазинах: \n" + productAnalytics.existInAll());
        System.out.println("Товары имеющиеся хотя бы в одном магазине: \n" + productAnalytics.existAtListInOne());
        System.out.println("Товары, которых нет ни в одном магазине: \n" + productAnalytics.notExistInShops());
        System.out.println("Товары, которые есть только в одном магазине: \n" + productAnalytics.existOnlyInOne());
    }

   private List<Shop> shops;           //2.10 Создать private List<Shop> shops - список магазинов
   private List<Product> products;     //2.11 Создать private List<Product> products -
    // список всех имеющихся в ассортименте товаров.
    // Все товары, присутствующие в магазинах, обязательно присутствуют в products,
    // но так же тут могут быть и товары, которых нет в магазинах.

    private ArrayList<Shop> shopArrayList;
    private ArrayList<Product> productArrayList;

//    //2.12 Создать конструктор
    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;         //Складывается впечатление, что эти две операции лишние в моем решении. Оставил как в задании.
        this.products = products;
        shopArrayList = new ArrayList<>(this.shops);            //Коллекция со списком всех магазинов
        productArrayList = new ArrayList<>(this.products);      //Коллекция со списком всех продуктов
    }

    //2.13 Товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll() {
        Set<Product> resultSet;
        if (shopArrayList.size() > 0) { //Проверка
            resultSet = new HashSet<>(shopArrayList.get(0).getProducts());  //Инициализация первым объектом (магазином) для сравнения с дальнйешими
            for (int i = 1; i < shopArrayList.size(); i++) {
                resultSet.retainAll(new ArrayList<>(shopArrayList.get(i).getProducts()));   //Поиск общих товаров
            }
            if (!(productArrayList.containsAll(resultSet))) {     //Проверка наличия товаров в общем списке
                resultSet.clear();
            }
        } else {
            resultSet = new HashSet<>();    //Возвращаю пустую коллекцию
        }

        return resultSet;
    }

    //2.14 Товары из products, которые имеются хотя бы в одном магазине
    public Set<Product> existAtListInOne() {
        Set<Product> resultSet = new HashSet<>();
        for (Product product : productArrayList) {
            for (Shop shop : shopArrayList) {
                ArrayList<Product> productFromShop = new ArrayList<>(shop.getProducts());
                if (productFromShop.contains(product)) {    //Если список товаров магазина содержит товар, добавляю в список
                    resultSet.add(product);
                }
            }
        }
        return resultSet;
    }

    //2.15 Товары из products, которых нет ни в одном магазине
    public Set<Product> notExistInShops() {
        Set<Product> resultSet = new HashSet<>(productArrayList);
        for (Shop shop : shopArrayList) {
            resultSet.removeAll(new ArrayList<>(shop.getProducts()));   //Из бщего списка вычитаю списки товаров магазинов
        }
        return resultSet;
    }

    //2.16 Товары из products, которые есть только в одном магазине
    public Set<Product> existOnlyInOne() {
        Set<Product> resultSet = new HashSet<>(existAtListInOne()); //Начальная коллекция на основе товаров, "которые имеются хотя бы в одном магазине"
        resultSet.removeAll(existInAll());  //Исключаем товары, которые есть во всех магазинах (возможно лишняя операция, но уменьшает коллекцию)

        for (Shop shop1 : shopArrayList){
            //Цикл проверки совпадений товаров
            for (Shop shop2 : shopArrayList){
                if(shopArrayList.indexOf(shop1) == shopArrayList.indexOf(shop2)){   //Пропускаю сравнение с самим собой
                    continue;   //Idea предлагает убрать. Оставлю для наглядности
                }else {
                    Set<Product> helpSet = new HashSet<>(shop1.getProducts());
                    helpSet.retainAll(shop2.getProducts()); //выбираю общие
                    resultSet.removeAll(helpSet);           //вычитаю результат
                }
            }
        }

        return resultSet;
    }

}





