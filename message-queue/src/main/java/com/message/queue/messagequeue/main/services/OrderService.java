package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.entities.Customer;
import com.message.queue.messagequeue.main.entities.Item;
import com.message.queue.messagequeue.main.entities.Order;
import com.message.queue.messagequeue.main.exceptions.CustomerNotFoundException;
import com.message.queue.messagequeue.main.repositories.ItemRepository;
import com.message.queue.messagequeue.main.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

//    List<Order> orderList;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Item> getItemsOfSpecifiedOrder(Long id){
        return itemRepository.getAllByCurrentOrderOrderId(id);
    }

    public Order createNewOrder(Customer customer) throws CustomerNotFoundException {

        Order order = new Order(customer);
        orderRepository.save(order);
//        orderList.add(order);

        return order;
    }

    public List<Order> getAllOrders(){
        return  orderRepository.findAll();
    }
}
