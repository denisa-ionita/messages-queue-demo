package com.message.queue.messagequeue.main.exceptions;

public class CustomerNotFoundException extends IllegalArgumentException {

    public CustomerNotFoundException(String message){
        super(message);
    }
}
