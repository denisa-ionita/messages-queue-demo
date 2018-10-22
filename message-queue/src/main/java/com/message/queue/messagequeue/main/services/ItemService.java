package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ItemService {


    List<Item> itemList;
    Queue<Item> itemsQueue;

    @Autowired
    LogFileService logFileService;

    public ItemService(){
        itemList = new ArrayList<>();
        itemsQueue = new LinkedList<>();
    }

    public void processItem(String name, double price){

        if(itemsQueue.size() == 10){

        }

//        item = new Item(name, 0);
//        itemList.add(item);
    }
}
