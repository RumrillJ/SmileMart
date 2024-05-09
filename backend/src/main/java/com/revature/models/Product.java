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

    // user likes? ManyToMany?

    // category foreign key?
    // what about multiple categories? ManyToMany?
    // some categories preclude others
    // clothing types: shoes, tops, headware, etc
    // demographic: men, women, kids
    // material: cotton, polyester, etc

    private String name;

    private double cost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    // Constructors

    public Product(int productId, String name, double cost, Category category) {
        this.productId = productId;
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


    // To String
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                '}';
    }
}
