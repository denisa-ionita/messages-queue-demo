package com.message.queue.messagequeue.main.repositories;

import com.message.queue.messagequeue.main.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
