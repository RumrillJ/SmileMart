package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "products")
@Component
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(nullable = false, unique = true)
    private String name;

    private double cost;

    private String description;

    private String image;

    // cascadetype.all prevents deletion of products
    @ManyToOne(fetch= FetchType.EAGER) //cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    // Constructors

    public Product(int productId, String name, double cost, Category category, String description, String image) {
        this.productId = productId;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public Product(String name, double cost, Category category) {
        this.name = name;
        this.cost = cost;
        this.category = category;
    }

    public Product() {
    }

    // Getters & Setters

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // To String
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
