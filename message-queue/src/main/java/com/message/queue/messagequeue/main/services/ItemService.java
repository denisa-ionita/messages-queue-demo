package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.Customer;
import com.message.queue.messagequeue.main.components.Item;
import com.message.queue.messagequeue.main.components.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ItemService {

    Order order;
    List<Item> itemList;
    Queue<Item> itemsQueue;
    @Autowired
    LogFileService logFileService;

    public ItemService(){
        itemList = new ArrayList<>();
        itemsQueue = new LinkedList<>();
    }

    public void processRequest(Customer customer, String itemName, double itemPrice){

        Item item = new Item(itemName, itemPrice);

        processOrder(customer, item);
    }

    public void processOrder(Customer customer, Item item){
        if(order.getCustomer().getName().compareTo(customer.getName()) == 1)
            order = new Order(customer);

        order.addNewItemToOrder(item);

        processQueue(item);
    }

    public void processQueue(Item item){

        itemsQueue.add(item);

        logFileService.writeLogFile(itemsQueue);
    }

}
