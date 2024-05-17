package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.dtos.OutgoingProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }


    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productDAO.findAll();
    }


    public boolean addProduct(Product product, int productId, int categoryId, String categoryDesc) {
        Optional<Product> products = productDAO.findById(productId);
        Optional<Category> categories = categoryDAO.findById(categoryId);

        if (products.isPresent()) {
            log.warn("Product already exists");
            return false;

        } else if (categories.isPresent()) {
            log.info("Adding product to category {}", categories.get().getDescription());
            product.setCategory(categories.get());

        } else {
            log.info("Creating new category");
            product.setCategory(new Category(categoryId, categoryDesc));
        }

           productDAO.save(product);
            log.info("New product added");
            return true;
        }

    public List<OutgoingProductDTO>   findAllByProductName(String pname) {
        List<Product> allPrd = productDAO.findAllByName(pname);
        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd) {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());
            log.info("Product found by name");
            outprd.add(outR);
        }

        return outprd;
    }

    public List<OutgoingProductDTO> showAllProductByPrice(double price) {
        List<Product> allPrd = productDAO.findAllByCostLessThan(price);
        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd) {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());
            log.info("Product found by price");
            outprd.add(outR);
        }

        return outprd;
    }

    public List<OutgoingProductDTO> findAllByCategoryCategoryId(int  cId) {
        List<Product> allPrd = productDAO.findAllByCategoryCategoryId(cId);
        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd) {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());
            log.info("Product found by category");
            outprd.add(outR);
        }

        return outprd;
    }
}



