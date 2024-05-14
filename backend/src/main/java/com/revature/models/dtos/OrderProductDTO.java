package com.revature.models.dtos;

public class OrderProductDTO {
    private int orderProductId;
    private int orderId;
    private int productId;
    private int quantity;

    public OrderProductDTO() {
    }

    public OrderProductDTO(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
