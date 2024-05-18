package com.revature.services;

import com.revature.daos.CategoryDAO;
import com.revature.daos.ProductDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Category;
import com.revature.models.Product;

import com.revature.models.User;
import com.revature.models.dtos.AddProductDTO;
import com.revature.models.dtos.OutgoingProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDAO productDAO;

    @Mock
    private CategoryDAO categoryDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @Mock
    private User principal;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {

        List<Product> expectedProducts = Collections.singletonList(new Product());
        when(productDAO.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getAllProducts();

        assertEquals(expectedProducts, actualProducts);
        verify(productDAO, times(1)).findAll();
    }

    @Test
    public void testAddProductShouldReturnTrueAfterProductIsAdded() {
        when(authentication.getPrincipal()).thenReturn(principal);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(principal.getRole()).thenReturn(User.ROLE.ADMIN);

        AddProductDTO newProduct = new AddProductDTO();
        newProduct.setCategory(new Category());

        when(categoryDAO.findByDescription(newProduct.getCategory().getDescription())).thenReturn(Optional.ofNullable(null));

        boolean isAdded = productService.addProduct(newProduct);

        assertTrue(isAdded);
        verify(categoryDAO, times(1)).findByDescription(newProduct.getCategory().getDescription());
    }

    @Test
    public void testAddProductShouldReturnFalseWhenProductExists() {
        when(authentication.getPrincipal()).thenReturn(principal);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(principal.getRole()).thenReturn(User.ROLE.ADMIN);

        AddProductDTO newProduct = new AddProductDTO();
        newProduct.setCategory(new Category());

        Product existingProduct = new Product();
        when(productDAO.findByNameAndCategoryCategoryDescription(newProduct.getName(), newProduct.getCategory().getDescription())).thenReturn(Optional.of(existingProduct));

        boolean isAdded = productService.addProduct(newProduct);

        assertFalse(isAdded);
        verify(productDAO, times(1)).findByNameAndCategoryCategoryDescription(newProduct.getName(), newProduct.getCategory().getDescription());
        verify(productDAO, never()).save(any());
    }

    @Test
    public void testFindAllByProductName_Found() {
        String productName = "test";
        Category category = new Category(0, "test category");
        Product product = new Product();
        product.setProductId(1);
        product.setName(productName);
        product.setCost(0);
        product.setDescription("test description");
        product.setCategory(category);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        OutgoingProductDTO productDTO = new OutgoingProductDTO(productName, 0, "test description", category);
        List<OutgoingProductDTO> expectedProductsDTO = new ArrayList<>();
        expectedProductsDTO.add(productDTO);

        when(productDAO.findAllByName(productName)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.findAllByProductName(productName);

        assertEquals(expectedProductsDTO.size(), actualProducts.size());
        assertEquals(expectedProductsDTO.get(0).getName(), actualProducts.get(0).getName());
        verify(productDAO, times(1)).findAllByName(productName);
    }

    @Test
    public void testFindAllByProductName_NotFound() {
        String productName = "test";
        List<Product> expectedProducts = new ArrayList<>();

        when(productDAO.findAllByName(productName)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.findAllByProductName(productName);

        assertEquals(expectedProducts.size(), actualProducts.size());
        verify(productDAO, times(1)).findAllByName(productName);
    }

    @Test
    public void testShowAllProductsByPrice_Found() {
        double price = 100.0;
        Category category = new Category(0, "test category");
        Product product = new Product();
        product.setProductId(1);
        product.setName("test");
        product.setCost(50.0);
        product.setDescription("test description");
        product.setCategory(category);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        OutgoingProductDTO productDTO = new OutgoingProductDTO("test", 50.0, "test description", category);
        List<OutgoingProductDTO> expectedProductsDTO = new ArrayList<>();
        expectedProductsDTO.add(productDTO);

        when(productDAO.findAllByCostLessThan(price)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.showAllProductByPrice(price);

        assertEquals(expectedProductsDTO.size(), actualProducts.size());
        assertEquals(expectedProductsDTO.get(0).getName(), actualProducts.get(0).getName());
        verify(productDAO, times(1)).findAllByCostLessThan(price);
    }

    @Test
    public void testShowAllProductsByPrice_NotFound() {
        double price = 100.0;
        List<Product> expectedProducts = new ArrayList<>();

        when(productDAO.findAllByCostLessThan(price)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.showAllProductByPrice(price);

        assertEquals(expectedProducts.size(), actualProducts.size());
        verify(productDAO, times(1)).findAllByCostLessThan(price);
    }

    @Test
    public void testFindAllByCategoryCategoryId_ValidCategory() {
        int categoryId = 1;
        Category category = new Category(categoryId, "test category");

        Product product = new Product();
        product.setProductId(1);
        product.setName("test");
        product.setCost(50.0);
        product.setDescription("test description");
        product.setCategory(category);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        OutgoingProductDTO productDTO = new OutgoingProductDTO("test", 50.0, "test description", category);
        List<OutgoingProductDTO> expectedProductsDTO = new ArrayList<>();
        expectedProductsDTO.add(productDTO);

        when(productDAO.findAllByCategoryCategoryId(categoryId)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.findAllByCategoryCategoryId(categoryId);

        assertEquals(expectedProductsDTO.size(), actualProducts.size());
        assertEquals(expectedProductsDTO.get(0).getName(), actualProducts.get(0).getName());
        verify(productDAO, times(1)).findAllByCategoryCategoryId(categoryId);
    }

    @Test
    public void testFindAllByCategoryCategoryId_InvalidCategory() {
        int categoryId = 1;
        List<Product> expectedProducts = new ArrayList<>();

        when(productDAO.findAllByCategoryCategoryId(categoryId)).thenReturn(expectedProducts);

        List<OutgoingProductDTO> actualProducts = productService.findAllByCategoryCategoryId(categoryId);

        assertEquals(expectedProducts.size(), actualProducts.size());
        verify(productDAO, times(1)).findAllByCategoryCategoryId(categoryId);
    }
}