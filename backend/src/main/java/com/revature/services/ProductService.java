package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.Category;
import com.revature.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }


    public List<Product> getAllProducts() {

        return productDAO.findAll();
    }

    public boolean addProduct(Product product, int categoryId, String categoryDesc) {

        Optional<Product> products = productDAO.findById(product.getProductId());

        Optional<Category> categories = categoryDAO.findById(categoryId);


        if (products.isPresent()) {

            return false;

        } else if (categories.isEmpty()) {

            product.setCategory(new Category(categoryId, categoryDesc));

        } else {

            product.setCategory(categories.get());

        }

            productDAO.save(product);

            return true;
        }

    }



