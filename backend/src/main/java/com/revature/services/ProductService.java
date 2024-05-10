package com.revature.services;

import com.revature.daos.ProductDAO;
import com.revature.models.Product;
import com.revature.models.dtos.OutgoingProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    /*public List<Product> findAllByCategoryCategoryId(int  cId) {

       return  productDAO.findAllByCategoryCategoryId(cId);
    }*/

    public List<OutgoingProductDTO> findAllByCategoryCategoryId(int  cId) {

        List<Product> allPrd = productDAO.findAllByCategoryCategoryId(cId);

        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd)
        {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());

            outprd.add(outR);
        }

        return outprd;
    }



    public List<OutgoingProductDTO>   findAllByProductName(String pname) {

        List<Product> allPrd = productDAO.findAllByName(pname);

        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd)
        {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());

            outprd.add(outR);
        }

        return outprd;
    }



    public List<OutgoingProductDTO> showAllProductByPrice(double price) {

        List<Product> allPrd = productDAO.findAllByCostLessThan(price);

        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd)
        {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
            p.getCost(),
            p.getDescription(),
            p.getCategory());

            outprd.add(outR);
        }

        return outprd;


    }
}
