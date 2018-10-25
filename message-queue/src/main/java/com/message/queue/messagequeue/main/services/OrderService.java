package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.Customer;
import com.message.queue.messagequeue.main.components.Order;
import com.message.queue.messagequeue.main.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order createNewOrder(Customer customer) throws CustomerNotFoundException {

        return new Order(customer);
    }

}
