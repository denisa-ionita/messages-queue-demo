package com.message.queue.messagequeue.main.components;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Order {

    Date orderDate;
    Customer customer;
    public static Long id = 0l;

    public Order(Customer customer) {
        this.id = this.id + 1;
        orderDate = new Date();
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

//    public void addNewItemToOrder(Item item){
//        Integer currentValue =0;
//
//        if (orderMap.get(item) != null)
//            currentValue = orderMap.get(item);
//
//        orderMap.put(item, currentValue + 1);
//
//    }

}
