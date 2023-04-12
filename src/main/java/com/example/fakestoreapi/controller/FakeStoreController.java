package com.example.fakestoreapi.controller;

import com.example.fakestoreapi.dao.FakeStoreDao;
import com.example.fakestoreapi.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class FakeStoreController {

    FakeStoreDao dao = new FakeStoreDao();

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(required = false) int id) {
        Product product = dao.getAllProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Product not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable(required = false) String category) {
        List<Product> products = dao.getAllProducts().stream().filter(p -> p.getCategory().equals(category)).toList();
        if (products.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Category not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}
