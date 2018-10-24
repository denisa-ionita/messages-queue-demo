package com.message.queue.messagequeue.main.components;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Customer {

    private String name;
    private Date birthday;

    public Customer() {
    }

    public Customer(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
