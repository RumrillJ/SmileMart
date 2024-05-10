package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table( name = "category")
@Component
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int categoryId;

    private String description;

    @OneToMany(mappedBy = "productId")
    private List<Product> products;

    // Constructors


    public Category(int categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;
    }

    public Category() {
    }

    // Getters and Setters

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    // To String

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }
}
