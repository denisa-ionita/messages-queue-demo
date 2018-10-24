package com.message.queue.messagequeue.main.components;

import com.message.queue.messagequeue.main.services.LogFileService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutomatedProcess {

    LogFileService readFile;

    public AutomatedProcess(){
        readFile = new LogFileService();
    }

    @Scheduled(cron = "0 0-5 18 * * *")
    public void systemUpdate(){
        readFile.createDailySystemStatus();
    }
}
