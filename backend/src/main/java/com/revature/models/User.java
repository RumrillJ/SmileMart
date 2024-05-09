package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Component
public class User {

    public enum ROLE {
        USER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ROLE role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    // generate getters and setters and constructor
    public User(int userId, String name, ROLE role, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
