package com.revature.daos;

import com.revature.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    public Optional<Category> findByDescription(String description);
}
