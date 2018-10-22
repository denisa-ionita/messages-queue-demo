package com.message.queue.messagequeue.main.controller;

import com.message.queue.messagequeue.main.components.Item;
import com.message.queue.messagequeue.main.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(path = "/items/{name}/{price}")
    public void postNewOrder(@PathVariable(value = "name") String name, @PathVariable(value = "price") double price){

        itemService.processItem(name, price);

    }
//
//    @GetMapping(path = "/dailyStatus")
//    public void writeDailyStatus(){
//        AutomatedProcess automatedProcess = new AutomatedProcess();
//
//        automatedProcess.systemUpdate();
//    }

}
