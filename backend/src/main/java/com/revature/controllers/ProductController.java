package com.revature.controllers;

import com.revature.daos.ProductDAO;
import com.revature.models.Product;
//import com.revature.services.ProductService;

import java.util.List;
import java.util.Optional;

import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(value = "/products")
@RestController
public class ProductController {
    private final ProductDAO productDAO;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductDAO productDAO, ProductService productService) {

        this.productDAO = productDAO;
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {


            return ResponseEntity.ok().body(productService.getAllProducts());

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


    @PostMapping("/{categoryId}/{categoryDesc}")
    public ResponseEntity<Object> addProduct(@RequestBody Product product, @PathVariable int categoryId, @PathVariable String categoryDesc) {

        if (productService.addProduct(product, categoryId, categoryDesc)) {

            return ResponseEntity.ok().body(product.getName() + " has been added");
        } else {

            return ResponseEntity.status(404).body(product.getName() + " already exists");
        }
    }



}
