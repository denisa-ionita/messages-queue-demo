package com.message.queue.messagequeue.main.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long orderId;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
    private Customer currentCustomer;
//
//    @OneToMany(mappedBy = "currentOrder", cascade = CascadeType.ALL)
//    private List<Item> items;

    public Order(){

    }

    public Order(Customer currentCustomer) {
        orderDate = new Date();
        this.currentCustomer = currentCustomer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


}
