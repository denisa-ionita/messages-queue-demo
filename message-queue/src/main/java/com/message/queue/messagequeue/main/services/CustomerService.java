package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.entities.Customer;
import com.message.queue.messagequeue.main.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    List<Customer> customers;

    @Autowired
    CustomerRepository customerRepository;

    public CustomerService(){
        customers = customerRepository.findAll();
    }

    public Long getCustomerId(Customer customer){

        Customer currentCustomer;

        if(findCustomer(customer) == true)
            currentCustomer =  customerRepository.getOne(customer.getCustomerId());
        else
        {
            currentCustomer = customerRepository.save(customer);
        }

        return currentCustomer.getCustomerId();
    }

    public boolean findCustomer(Customer customer){

        boolean isFound = false;

        if(customers.size() > 0){
            for(Customer c: customers){
                if(c.equals(customer))
                    isFound = true;
            }
        }

        return isFound;
    }
}
