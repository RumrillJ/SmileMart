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
}
