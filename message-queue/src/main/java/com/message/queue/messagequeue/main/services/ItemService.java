package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.Customer;
import com.message.queue.messagequeue.main.components.Item;
import com.message.queue.messagequeue.main.components.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ItemService {

    Queue<Item> itemsQueue;
    public static Map<Long, List<Item>> orderMap;

    @Autowired
    LogFileService logFileService;

    public ItemService(){
        itemsQueue = new LinkedList<>();
    }

    public void processRequest(String itemName, double itemPrice, Long orderId){

        Item item = new Item(itemName, itemPrice, orderId);

        System.out.println(item.toString());
        processQueue(item);
    }

    public void processQueue(Item item){

        itemsQueue.add(item);

        if(itemsQueue.size() == 10){
            finalizeOrder();
        }

    }

    public void finalizeOrder(){
        logFileService.writeLogFile(itemsQueue);
    }

    public void getOrderMap(){

        List<Item> items;

        for(Item item: itemsQueue){

            if(orderMap.get(item.getOrderId()) == null){
                items = new ArrayList<>();
            }
            else
            {
                items = orderMap.get(item.getOrderId());
            }
            items.add(item);
        }

        logFileService.writeOrderMapFileLog(orderMap);
    }

}
