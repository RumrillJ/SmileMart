package com.revature.models.dtos;


public class AddProductDTO {

    private String name;
    private double cost;
    private String description;
    private String categoryDescription;

    public AddProductDTO() {
    }

    public AddProductDTO(String name, double cost, String description, String categoryDescription) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.categoryDescription = categoryDescription;
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

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategory(String category) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "AddProductDTO{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", category=" + categoryDescription +
                '}';
    }
}
