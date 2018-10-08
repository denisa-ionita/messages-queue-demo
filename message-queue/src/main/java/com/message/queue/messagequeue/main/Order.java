package com.message.queue.messagequeue.main;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Double> cantities;
    private List<Item> items;

    public Order(){
        this.cantities = new ArrayList<>();
        this.items = new ArrayList<>();
    }


    public List<Double> getCantities() {
        return cantities;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addAItem(Item item, Double cantity)
    {
        this.items.add(item);
        this.cantities.add(cantity);
    }

}
