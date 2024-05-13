package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Component
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    // products in order? ManyToMany?
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    Set<OrderProduct> products;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    private Date date;

    // Constructors
    public Order() {
    }

    public Order(User user, Status status, Date date) {
        this.user = user;
        this.status = status;
        this.date = date;
    }

    public Order(int orderId, User user, Set<OrderProduct> products, Status status, Date date) {
        this.orderId = orderId;
        this.user = user;
        this.products = products;
        this.status = status;
        this.date = date;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // toString
    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", products=" + products +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
