package com.revature.models.dtos;

import com.revature.models.Category;
import com.revature.models.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OutProductDTO {

    private int productId;
    private String name;
    private double cost;
    private String description;
    private String category;

    public OutProductDTO() {
    }
    public OutProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.cost = product.getCost();
        this.description = product.getDescription();
        this.category = product.getCategory().getDescription();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "OutProductDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
