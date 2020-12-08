package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    /*public static void main(String[] args) {
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

        ProductAnalytics productAnalytics = new ProductAnalytics(shops, products);
        System.out.println("Товары имеющиеся во всех магазинах: \n" + productAnalytics.existInAll());
        System.out.println("Товары имеющиеся хотя бы в одном магазине: \n" + productAnalytics.existAtListInOne());
        System.out.println("Товары, которых нет ни в одном магазине: \n" + productAnalytics.notExistInShops());
        System.out.println("Товары, которые есть только в одном магазине: \n" + productAnalytics.existOnlyInOne());
    }*/

    List<Shop> shops;           //2.10 Создать private List<Shop> shops - список магазинов
    List<Product> products;     //2.11 Создать private List<Product> products -
    // список всех имеющихся в ассортименте товаров.
    // Все товары, присутствующие в магазинах, обязательно присутствуют в products,
    // но так же тут могут быть и товары, которых нет в магазинах.

//    ArrayList<Shop> shopArrayList;
//    ArrayList<Product> productArrayList;

    //2.12 Создать конструктор
    public ProductAnalytics(List<Shop> shops, List<Product> products) {
//        this.shops = shops;
//        this.products = products;
//            if (this.shops != null && this.products != null) {
//        shopArrayList = new ArrayList<>(shops);
//        productArrayList = new ArrayList<>(products);
//            }
    }

    /*public class Find {
//            List<Shop> shops;
//            List<Product> products;
//            public Find(List<Shop> shops, List<Product> products){
//                shops = shops;
//                this.products = products;
//            ArrayList<Shop> shopArrayList = new ArrayList<>(shops);
//            ArrayList<Product> productArrayList = new ArrayList<>(products);

        public Set<Product> doFind() {
            Set<Product> resultSet = new HashSet<>();


            for (Product product : productArrayList) {
                for (Shop shop : shopArrayList) {
                    for (Product productFromShop : shop.getProducts()) {
//                            if (product.getCode() == productFromShop.getCode()){          //Перекрыть Equals для Product
                        if (product.equals(productFromShop)) {
//                        ArrayList<Product> productFromShop = new ArrayList<>(shop.getProducts());
//                        if (productFromShop.contains(product)){
                            resultSet.add(product);
                        }
                    }


                }
            }

            return resultSet;
        }

        //Куски кода из методов
        //            Find find = new Find();
//            HashSet<Product> resultSet = new HashSet<>(find.doFind());
        //                for(Product product:productArrayList){
//                    for(Shop shop:shopArrayList){
//                        ArrayList<Product> productFromShop = new ArrayList<>(shop.getProducts());
//
//                    }
//                }
//                            for (Product productFromShop:shop.getProducts()){
//                                if (product.equals(productFromShop)){
//                                    resultSet.add(product);
//                                }
//                        }

    }*/

    //2.13 Товары из products, которые имеются во всех магазинах
    /*public Set<Product> existInAll() {
        Set<Product> resultSet;
        if (shopArrayList.size() > 0) {
            resultSet = new HashSet<>(shopArrayList.get(0).getProducts());
            for (int i = 1; i < shopArrayList.size(); i++) {
                resultSet.retainAll(new ArrayList<>(shopArrayList.get(i).getProducts()));
            }
            if (!(productArrayList.containsAll(resultSet))) {     //Проверка наличия товаров в общем списке
                resultSet.clear();
            }
        } else {
            resultSet = new HashSet<>();
        }

        return resultSet;
    }*/

    //2.14 Товары из products, которые имеются хотя бы в одном магазине
    /*public Set<Product> existAtListInOne() {
        Set<Product> resultSet = new HashSet<>();
        for (Product product : productArrayList) {
            for (Shop shop : shopArrayList) {
                ArrayList<Product> productFromShop = new ArrayList<>(shop.getProducts());
                if (productFromShop.contains(product)) {
                    resultSet.add(product);
                }
            }
        }
        return resultSet;
    }*/

    //2.15 Товары из products, которых нет ни в одном магазине
    /*public Set<Product> notExistInShops() {
        Set<Product> resultSet = new HashSet<>(productArrayList);
        for (Shop shop : shopArrayList) {
            resultSet.removeAll(new ArrayList<>(shop.getProducts()));
        }
        return resultSet;
    }*/

    //2.16 Товары из products, которые есть только в одном магазине
    /*public Set<Product> existOnlyInOne() {
        Set<Product> resultSet = new HashSet<>(existAtListInOne());
        resultSet.removeAll(existInAll());
        for (int i = 0; i < shopArrayList.size() - 1; i++) {
            Set<Product> helpSet = new HashSet<>(shopArrayList.get(i).getProducts());
            helpSet.retainAll(shopArrayList.get(i + 1).getProducts());
            resultSet.removeAll(helpSet);
        }

        return resultSet;
    }*/


    //2.1 Создать класс Product - товар,
    //2.2. Создать private String code - уникальный артикул товара
/*    public static class Product {
        private String code;

        //2.3 Создать конструктор public Product(String code)
        public Product(String code) {
            this.code = code;
        }

        //2.4 Метод public String getCode()
        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return code.equals(product.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }*/

    //2.5 Создать класс Shop - магазин
    //2.6 Создать private List<Product> products - товары имеющиеся в магазине
/*    public static class Shop {
        List<Product> products;

        //2.7 Создать конструктор public Shop(List<Product> products)
        public Shop(List<Product> products) {
            this.products = products;
        }

        //2.8 Создать метод public List<Product> getProducts()
        public List<Product> getProducts() {
            return products;
        }
    }*/


}





