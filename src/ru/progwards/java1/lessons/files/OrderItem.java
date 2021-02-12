package ru.progwards.java1.lessons.files;

public class OrderItem {

    public String goodsName;    //наименование товара
    public int count;           //количество
    public double price;        //цена за единицу

    //Конструктор класса
    public OrderItem(String itemLine) {
        String[] itemInfoArray = itemLine.split(",");
        this.goodsName = itemInfoArray[0];
        this.count = Integer.parseInt(itemInfoArray[1].trim());
        this.price = Double.parseDouble(itemInfoArray[2].trim());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
