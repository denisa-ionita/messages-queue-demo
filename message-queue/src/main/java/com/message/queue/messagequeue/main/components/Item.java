package com.message.queue.messagequeue.main.components;

import org.springframework.stereotype.Component;

@Component
public class Item {

    private String name;
    private double price;
    private Long orderId;

    public  Item()
    {

    }

    public Item(String name, double price, Long orderId) {
        this.name = name;
        this.price = price;
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", orderId=" + orderId +
                '}';
    }
}
