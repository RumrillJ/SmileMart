package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.daos.ProductDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.dtos.AddProductDTO;
import com.revature.models.dtos.OutgoingProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final UserDAO userDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, CategoryDAO categoryDAO, UserDAO userDAO){
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
    }


    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productDAO.findAll();
    }


    public boolean addProduct(AddProductDTO productDTO) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            log.warn("You are not logged in");
            return false;
        }

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getRole().equals(User.ROLE.USER)) {
            log.warn("User does not have permission to add product");
            return false;
        }

        Optional<Product> optProduct = productDAO.findByNameAndCategoryDescription(productDTO.getName(), productDTO.getCategory());
        if(optProduct.isPresent()) {
            log.warn("Product already exists");
            return false;
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCost(productDTO.getCost());

        Optional<Category> categories = categoryDAO.findByDescription(productDTO.getCategory());

        if (categories.isPresent()) {
            log.info("Adding product to category {}", categories.get().getDescription());
            product.setCategory(categories.get());

        } else {
            log.info("Creating new category");
            Category c = new Category();
            c.setDescription(productDTO.getCategory());
            categoryDAO.save(c);
            product.setCategory(c);
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



