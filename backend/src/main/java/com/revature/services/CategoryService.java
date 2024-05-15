package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }


    public List<Category> getAllCategories() {

        return categoryDAO.findAll();
    }
}
