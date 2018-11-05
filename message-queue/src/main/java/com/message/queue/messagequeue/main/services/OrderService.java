package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.entities.Customer;
import com.message.queue.messagequeue.main.entities.Order;
import com.message.queue.messagequeue.main.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    static List<Order> orderList;

    public OrderService(){
        orderList = new ArrayList<>();
    }

    public Order createNewOrder(Customer customer) throws CustomerNotFoundException {

        Order order = new Order(customer);
        orderList.add(order);

        return order;
    }

}
