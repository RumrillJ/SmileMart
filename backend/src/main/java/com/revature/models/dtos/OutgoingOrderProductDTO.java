package com.revature.models.dtos;


public class OutgoingOrderProductDTO {

    private int orderProductId;

    private OutgoingProductDTO product;

    private int quantity;

    public OutgoingOrderProductDTO() {
    }

    public OutgoingOrderProductDTO(int orderProductId, OutgoingProductDTO product, int quantity) {
        this.orderProductId = orderProductId;
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public OutgoingProductDTO getProduct() {
        return product;
    }

    public void setProduct(OutgoingProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
