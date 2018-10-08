package com.message.queue.messagequeue.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutomatedProcess {

    LogFileComponent readFile;

    public AutomatedProcess(){
        readFile = new LogFileComponent();
    }

    @Scheduled(cron = "0 0-5 18 * * *")
    public void systemUpdate(){
        readFile.createDailySystemStatus();
    }
}
