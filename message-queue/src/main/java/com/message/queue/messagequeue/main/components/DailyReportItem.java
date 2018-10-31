package com.message.queue.messagequeue.main.components;

import org.springframework.stereotype.Component;

@Component
public class DailyReportItem {

    private Item item;
    private Integer noItems;

    public DailyReportItem(){

    }

    public DailyReportItem(Item item, Integer noItems) {
        this.item = item;
        this.noItems = noItems;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getNoItems() {
        return noItems;
    }

    public void setNoItems(Integer noItems) {
        this.noItems = noItems;
    }

    @Override
    public boolean equals(Object obj) {

        DailyReportItem currentObj = (DailyReportItem) obj;

        return this.getItem().equals(currentObj.getItem());
    }

    @Override
    public String toString() {
        return "DailyReportItem{" +
                "item=" + item +
                ", noItems=" + noItems +
                '}';
    }
}
