package com.revature.models.dtos;

import com.revature.models.OrderProduct;
import com.revature.models.Status;

import java.util.Date;
import java.util.Set;

public class OutgoingOrderDTO {

    private int orderId;

    private int userId;

    private Set<OrderProduct> products;

    private Status status;

    private Date date;

    //boilerplate

    public OutgoingOrderDTO() {
    }

    public OutgoingOrderDTO(int orderId, int userId, Set<OrderProduct> products, Status status, Date date) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = products;
        this.status = status;
        this.date = date;
    }

    //getter/setter
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //toString
    @Override
    public String toString() {
        return "OutgoingOrderDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", products=" + products +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
