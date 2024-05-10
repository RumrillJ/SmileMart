package com.revature.models.dtos;

public class UserRegistrationDTO {

    private String name;
    private String password;
    private String email;

    // Constructor
    public UserRegistrationDTO(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserRegistrationDTO() {
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
