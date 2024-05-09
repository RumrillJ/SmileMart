package com.revature.controllers;

import com.revature.daos.ProductDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Product;
//import com.revature.services.ProductService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

public class ProductController {
    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct (@PathVariable int productId) {
        Optional<Product> b = productDAO.findById(productId);
        if (b.isEmpty()) {
            return ResponseEntity.status(404).body("No product at ID " + productId + "found");
        }

        Product product = b.get();

        productDAO.deleteById(productId);
        return ResponseEntity.ok().body(product.getName() + " deleted from Products");
    }
}
