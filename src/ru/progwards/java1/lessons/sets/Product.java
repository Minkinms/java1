package ru.progwards.java1.lessons.sets;

import java.util.Objects;

public class Product {
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
        return code.equals(product.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
