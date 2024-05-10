package com.revature.models.dtos;

public class UserLoginDTO {

    private String email;

    private String password;

    // Constructors

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLoginDTO() {}

    // Getters & Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // To String

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
