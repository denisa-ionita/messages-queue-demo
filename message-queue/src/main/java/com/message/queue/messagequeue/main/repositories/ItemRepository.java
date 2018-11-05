package com.message.queue.messagequeue.main.repositories;

import com.message.queue.messagequeue.main.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
