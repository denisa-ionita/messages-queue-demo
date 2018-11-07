package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.entities.Item;
import com.message.queue.messagequeue.main.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

    Queue<Item> itemsQueue;
//    public static Map<Long, List<Item>> orderMap;

    @Autowired
    LogFileService logFileService;

    @Autowired
    ItemRepository itemRepository;

    public ItemService() {

        itemsQueue = new LinkedList<>();
//        orderMap = new HashMap<>();
    }

    public void processRequest(String itemName, double itemPrice, Long orderId) {

//        Item item = new Item(itemName, itemPrice, orderId);
        Item item = new Item(itemName, itemPrice);

        itemRepository.save(item);

//        System.out.println(item.toString());
        processQueue(item);
    }

    public void processQueue(Item item) {

        itemsQueue.add(item);
//        addToOrderMap(item);

        if (itemsQueue.size() == 10) {
            finalizeOrder();
        }

    }

    public void finalizeOrder() {
        logFileService.writeLogFile(itemsQueue);
    }

//    public void addToOrderMap(Item item) {
//
//        List<Item> items;
//
//        if (orderMap.get(item.getCurrentOrder().getOrderId()) == null) {
//            items = new ArrayList<>();
//        } else {
//            items = orderMap.get(item.getCurrentOrder().getOrderId());
//        }
//        items.add(item);
//        orderMap.put(item.getCurrentOrder().getOrderId(), items);
////        logFileService.writeOrderMapFileLog(orderMap);
//    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

}
