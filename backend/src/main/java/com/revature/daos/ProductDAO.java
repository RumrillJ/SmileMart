package com.revature.daos;

import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    public List<Product> findAllByCategoryCategoryId(int cId);

    public List<Product> findAllByName(String pname);

    public List<Product> findAllByCostLessThan(double price);
}
