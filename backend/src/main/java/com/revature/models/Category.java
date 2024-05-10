package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Product> products;

    // Constructors
    public Category(int categoryId, String description, List<Product> products) {
        this.categoryId = categoryId;
        this.description = description;
        this.products = products;
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

    @JsonIgnore
    public List<Product> getProducts() {
        return products;
    }

    @JsonIgnore
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
