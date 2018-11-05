package com.message.queue.messagequeue.main.components;

import com.message.queue.messagequeue.main.entities.Item;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReportItem that = (DailyReportItem) o;
        return Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item);
    }

    @Override
    public String toString() {
        return "DailyReportItem{" +
                "item=" + item +
                ", noItems=" + noItems +
                '}';
    }
}
