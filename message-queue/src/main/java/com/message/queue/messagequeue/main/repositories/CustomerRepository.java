package com.message.queue.messagequeue.main.repositories;

import com.message.queue.messagequeue.main.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
