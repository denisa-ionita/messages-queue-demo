package com.message.queue.messagequeue.main.services;

import com.message.queue.messagequeue.main.components.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class LogFileService {

    File file;

    Map<Item, Integer> itemsMap;

    public LogFileService(){
        file = new File("logFile.txt");
        itemsMap = new HashMap<>();
    }

    public void writeLogFile(List<Item> items){
        BufferedWriter writer = null;
        try {
            String timeLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("[" + timeLog + "]" + System.lineSeparator());
            for(Item item: items){
                writer.write("Item-ul " + item.getName() + " a fost achizitionat " + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public Set<String> findDuplicates(List<Item> listContainingDuplicates)
    {
        Set<String> setToReturn = new HashSet();

        for (Item item : listContainingDuplicates)
        {
            setToReturn.add(item.getName());
        }


        return setToReturn;
    }

    public void readLogFile(){

        List<Item> itemsExtractedFromLogFile = new ArrayList<>();

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));

            String line;
            Item newItemExtracted;
            String[] itemsOfCurrentLine;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null){

                if(lineNumber%11 != 1) {
                    System.out.println("Line no: " + lineNumber);
                    itemsOfCurrentLine = line.split(" ");
                    newItemExtracted = new Item();

                    newItemExtracted.setName(itemsOfCurrentLine[1]);

                    itemsExtractedFromLogFile.add(newItemExtracted);
                }
                else
                {
                    System.out.println("Procesare coada de 10 msg");
                }

                lineNumber++;
            }

            System.out.println("itemsFromLogFIle: " + itemsExtractedFromLogFile.size());

            Set<String> distinctItems = findDuplicates(itemsExtractedFromLogFile);

            System.out.println("distinctItems no: "+distinctItems.size());

            for(String distinctItem: distinctItems){

                System.out.println("Distinct item: "+distinctItem);
                int number = 0;
                for(Item item: itemsExtractedFromLogFile){
                    if(item.getName().compareTo(distinctItem) == 0)
                        number++;
                }
                itemsMap.put(new Item(distinctItem, 0), number);

            }



    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDailySystemStatus(){
        BufferedWriter dailySystemStatusWriter = null;
        try {
            String timeLog = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

            dailySystemStatusWriter = new BufferedWriter(new FileWriter(timeLog + ".txt"));

            readLogFile();

            for (Map.Entry<Item, Integer> entry : itemsMap.entrySet()) {
                dailySystemStatusWriter.write("Item-ul " + entry.getKey().getName() + " a fost achizitionat astazi de " + entry.getValue() + " ori. " + System.lineSeparator());
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
