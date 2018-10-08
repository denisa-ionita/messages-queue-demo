package com.message.queue.messagequeue.main;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    Item item;
    List<Item> itemList;
    LogFileComponent logFileComponent;

    public ItemController(){
        this.itemList = new ArrayList<>();
        this.logFileComponent = new LogFileComponent();
    }

    @GetMapping(path = "/items/{name}")
    public Item postNewOrder(@PathVariable(value = "name") String name){

        if(itemList.size() == 9){
            System.out.println("Se scriu cele 10 mesaje in fisierul de log-uri");
            logFileComponent.writeLogFile(this.itemList);
            itemList = new ArrayList<>();

            System.out.println("Coada noua de 10 msg");
        }

        item = new Item(name, 0);
        itemList.add(item);
        System.out.println("S-a cerut produsul " + item.getName());

        return item;
    }

    @GetMapping(path = "/dailyStatus")
    public void writeDailyStatus(){
        AutomatedProcess automatedProcess = new AutomatedProcess();

        automatedProcess.systemUpdate();
    }

}
