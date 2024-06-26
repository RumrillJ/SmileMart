package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;



@Entity
@Table( name = "categories")
@Component
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String description;

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

    // To String

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }
}