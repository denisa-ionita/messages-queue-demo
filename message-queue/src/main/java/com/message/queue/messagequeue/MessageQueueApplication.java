package com.message.queue.messagequeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.message.queue.messagequeue.main.repositories")
public class MessageQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageQueueApplication.class, args);
	}
}
