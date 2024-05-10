package com.revature.services;

import com.revature.daos.ProductDAO;
import com.revature.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    public List<Product> getAllProducts() {

        return productDAO.findAll();
    }

    public boolean addProduct(int productId, Product product) {

        Optional<Product> products = productDAO.findById(productId);

        if (products.isPresent()) {

            return false;
        } else {

            productDAO.save(product);

            return true;
        }

    }


    public List<Product> findAllByCategoryCategoryId(int  cId) {

       return  productDAO.findAllByCategoryCategoryId(cId);
    }



    public List<Product>   findAllByProductName(String pname) {


        return productDAO.findAllByName(pname);
    }



    public List<Product> showAllProductByPrice(double price) {

        return productDAO.findAllByCostLessThan(price);


    }
}
