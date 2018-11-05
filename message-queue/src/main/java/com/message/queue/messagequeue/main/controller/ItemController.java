package com.message.queue.messagequeue.main.controller;

import com.message.queue.messagequeue.main.components.AutomatedProcess;
import com.message.queue.messagequeue.main.entities.Customer;
import com.message.queue.messagequeue.main.entities.Order;
import com.message.queue.messagequeue.main.services.ItemService;
import com.message.queue.messagequeue.main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class ItemController {

    public Customer customer;
    public Order order;

    AutomatedProcess automatedProcess;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/customer/{customerName}/{birthday}")
    public void postNewCustomer(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "birthday") String birthday){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");

        try {
            customer = new Customer(customerName,simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/newOrder")
    public void createNewOrder(){
        order = orderService.createNewOrder(customer);
    }

    @PostMapping(path = "/order/{orderId}/{itemName}/{itemPrice}")
    public void requestNewItem(@PathVariable(value = "orderId") Long orderId,
                             @PathVariable(value = "itemName") String itemName,
                             @PathVariable(value = "itemPrice") double itemPrice){

        itemService.processRequest(itemName, itemPrice, orderId);

    }

    @PostMapping(path = "/finalizeOrder")
    public void postFinalizeOrder(){
        itemService.finalizeOrder();
    }

    @GetMapping(path = "/dailyStatus")
    public void writeDailyStatus(){
        automatedProcess = new AutomatedProcess();

        automatedProcess.systemUpdate();
    }

}
