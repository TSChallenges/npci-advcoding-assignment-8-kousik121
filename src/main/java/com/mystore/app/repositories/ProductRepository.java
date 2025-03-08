package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByNameContainsIgnoreCase(String name);
    public List<Product> findByCategory(String category);
    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    public List<Product> findByStockQuantityBetween(Integer minStock, Integer maxStock);

}
