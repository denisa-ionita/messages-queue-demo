package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.DailyReportItem;
import com.message.queue.messagequeue.main.entities.Item;
import com.message.queue.messagequeue.main.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class LogFileService {

    File file;
    StringBuilder stringBuilder;
    BufferedWriter writer = null;

    List<DailyReportItem> dailyReportItemsList;

    @Autowired
    ItemService itemService;


    public LogFileService() {
        file = new File("logFile.txt");
        stringBuilder = new StringBuilder();
        dailyReportItemsList = new ArrayList<>();
    }

    public void writeLogFile(Queue<Item> itemsQueue) {

        String timeLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        try {
            writer = new BufferedWriter(new FileWriter(file, true));

            System.out.println("Queue current size: " + itemsQueue.size());

            if (itemsQueue.size() == 10) {
                stringBuilder.append("[" + timeLog + "]" + System.lineSeparator());

                for (Iterator<Item> it = itemsQueue.iterator(); it.hasNext(); ) {

                    Item item = it.next();

//                    Order order = OrderService.orderList.get(item.getCurrentOrder().getOrderId().intValue() - 1);

                    //????
                    Order order = item.getCurrentOrder();

                    stringBuilder.append("Item-ul " + item.getName().toUpperCase() + " a fost achizitionat la pretul " + item.getPrice() + " in cadrul comenzii " + item.getCurrentOrder().getOrderId() + " de catre " + order.getCurrentCustomer().getName() + System.lineSeparator());

                    it.remove();
                }

                try {
                    System.out.println("Write details to logFile.txt  ...");
                    writer.write(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.setLength(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void createOrReplaceDailyReportItem(Item item) {


        ListIterator<DailyReportItem> it = dailyReportItemsList.listIterator();

        if (!dailyReportItemsList.contains(new DailyReportItem(item, 1)) || dailyReportItemsList.size() == 0) {
            dailyReportItemsList.add(new DailyReportItem(item, 1));
        } else {
            while (it.hasNext()) {
                DailyReportItem dailyReportItem = it.next();

                if (dailyReportItem.getItem().equals(item)) {

                    dailyReportItem.setNoItems(dailyReportItem.getNoItems() + 1);

//                    it.set(dailyReportItem);
                    break;
                }
            }


        }
    }


//    public void generateDailyReportItemList() {
//
//        for (Map.Entry<Long, List<Item>> entry : ItemService.orderMap.entrySet()) {
//
//            for (Item item : entry.getValue()) {
//                createOrReplaceDailyReportItem(item);
//            }
//        }
//
//    }

    public void createDailySystemStatus() {

//        generateDailyReportItemList();

        BufferedWriter dailySystemStatusWriter = null;
        try {
            String timeLog = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

            dailySystemStatusWriter = new BufferedWriter(new FileWriter(timeLog + ".txt"));

            System.out.println("DailyReportItemsList size: " + dailyReportItemsList.size());

            for (DailyReportItem item : dailyReportItemsList) {
                dailySystemStatusWriter.write(item.getItem().toString() + " a fost achizitionat astazi de " + item.getNoItems() + " ori => Valoare totala: " + item.getItem().getPrice() * item.getNoItems() + System.lineSeparator());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dailySystemStatusWriter.close();
            } catch (Exception e) {
            }
        }
    }
}
