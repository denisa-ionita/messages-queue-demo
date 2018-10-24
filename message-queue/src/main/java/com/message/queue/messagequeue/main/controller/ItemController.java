package com.message.queue.messagequeue.main.controller;

import com.message.queue.messagequeue.main.components.Customer;
import com.message.queue.messagequeue.main.components.Item;
import com.message.queue.messagequeue.main.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class ItemController {

    public static Customer customer;

    @Autowired
    ItemService itemService;

    @GetMapping(path = "/items/{itemName}/{itemPrice}")
    public void postNewOrder(@PathVariable(value = "itemName") String itemName, @PathVariable(value = "itemPrice") double itemPrice){

        itemService.processRequest(customer, itemName, itemPrice);

    }

    @PostMapping(params = "/customer/{customerName}/{birthday}")
    public void postNewCustomer(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "birthday") String birthday){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");

        try {
            customer = new Customer(customerName,simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping(path = "/dailyStatus")
//    public void writeDailyStatus(){
//        AutomatedProcess automatedProcess = new AutomatedProcess();
//
//        automatedProcess.systemUpdate();
//    }

}
