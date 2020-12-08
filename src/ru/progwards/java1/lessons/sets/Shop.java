package ru.progwards.java1.lessons.sets;

import java.util.List;

public class Shop {
    List<Product> products;

    //2.7 Создать конструктор public Shop(List<Product> products)
    public Shop(List<Product> products) {
        this.products = products;
    }

    //2.8 Создать метод public List<Product> getProducts()
    public List<Product> getProducts() {
        return products;
    }
}
