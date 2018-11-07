package com.message.queue.messagequeue.main.controller;

import com.message.queue.messagequeue.main.components.AutomatedProcess;
import com.message.queue.messagequeue.main.entities.Customer;
import com.message.queue.messagequeue.main.entities.Item;
import com.message.queue.messagequeue.main.entities.Order;
import com.message.queue.messagequeue.main.repositories.CustomerRepository;
import com.message.queue.messagequeue.main.repositories.OrderRepository;
import com.message.queue.messagequeue.main.services.CustomerService;
import com.message.queue.messagequeue.main.services.ItemService;
import com.message.queue.messagequeue.main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    AutomatedProcess automatedProcess;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(path = "/customer/{customerName}/{birthday}")
    public void postNewCustomer(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "birthday") String birthday){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");

        Customer customer = null;

        try {
            customer = new Customer(customerName,simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        customerRepository.save(customer);
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(path = "/customer/{customerId}/newOrder")
    public void createNewOrderWithCurrentCustomerId(@PathVariable(value = "customerId") Long customerId){

        Customer currentCustomer = customerRepository.getOne(customerId);

        Order order = orderService.createNewOrder(currentCustomer);

    }

    @GetMapping(path = "/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
//
//    @PostMapping(path = "/newOrder")
//    public void createNewOrder(){
//        order = orderService.createNewOrder(customer);
//    }

    @PostMapping(path = "/order/{orderId}/{itemName}/{itemPrice}")
    public void requestNewItem(@PathVariable(value = "orderId") Long orderId,
                             @PathVariable(value = "itemName") String itemName,
                             @PathVariable(value = "itemPrice") double itemPrice){

        itemService.processRequest(itemName, itemPrice, orderId);

    }

    @GetMapping(path = "/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping(path = "/finalizeOrder")
    public void postFinalizeOrder(){
        itemService.finalizeOrder();
    }

    @GetMapping(path = "/dailyStatus")
    public void writeDailyStatus(){
        automatedProcess.systemUpdate();
    }

}
