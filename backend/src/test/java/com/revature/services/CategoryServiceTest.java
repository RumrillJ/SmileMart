package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.models.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void testGetAllCategories() {
        // Given
        Category category1 = new Category(1, "Category 1");
        Category category2 = new Category(2, "Category 2");
        List<Category> categories = Arrays.asList(category1, category2);

        // Mock behavior
        when(categoryDAO.findAll()).thenReturn(categories);

        // When
        List<Category> retrievedCategories = categoryService.getAllCategories();

        // Then
        assertEquals(categories.size(), retrievedCategories.size());
        assertEquals(categories.get(0), retrievedCategories.get(0));
        assertEquals(categories.get(1), retrievedCategories.get(1));
    }
}