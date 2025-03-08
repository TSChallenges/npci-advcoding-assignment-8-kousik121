package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public Page<Product> getAllProducts(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("sortBy") String sortBy, @RequestParam("sortDir") String sortDir) {
        return productService.getAllProducts(page, pageSize, sortBy, sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Product> searchByProductName(@RequestParam("name") String name) {
        return productService.searchByProductName(name);
    }


    @GetMapping("/filter/category")
    public List<Product> searchByProductCategory(@RequestParam("category") String category) {
        return productService.searchByProductCategory(category);
    }


    @GetMapping("/filter/price")
    public List<Product> searchByPriceRange(@RequestParam("minPrice") Double minPrice, @RequestParam("maxPrice") Double maxPrice) {
        return productService.searchByProductPrice(minPrice, maxPrice);
    }


    @GetMapping("/filter/stock")
    public List<Product> searchByStockRange(@RequestParam("minStock") Integer minStock, @RequestParam("maxStock") Integer maxStock) {
        return productService.searchByProductStock(minStock, maxStock);
    }


}
