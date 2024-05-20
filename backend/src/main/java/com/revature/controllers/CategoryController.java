package com.revature.controllers;

import com.revature.models.Category;
import com.revature.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@CrossOrigin(origins = {"http://localhost:3000", "http://smilemarts3.s3-website.us-east-2.amazonaws.com/"}, allowCredentials = "true")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {


        return ResponseEntity.ok().body(categoryService.getAllCategories());

    }
}
