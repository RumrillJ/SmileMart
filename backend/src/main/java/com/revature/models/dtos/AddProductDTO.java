package com.revature.models.dtos;


import com.revature.models.Category;

public class AddProductDTO {

    private String name;
    private double cost;
    private String description;
    private Category category;

    public AddProductDTO() {
    }

    public AddProductDTO(String name, double cost, String description, Category category) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AddProductDTO{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
