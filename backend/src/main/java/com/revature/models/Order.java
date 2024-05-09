package com.revature.models;

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
    @OneToMany(mappedBy = "orderProductId")
    Set<OrderProduct> products;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    private Date date;
}
