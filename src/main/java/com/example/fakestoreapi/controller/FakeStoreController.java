package com.example.fakestoreapi.controller;

import com.example.fakestoreapi.dao.FakeStoreDao;
import com.example.fakestoreapi.model.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
public class FakeStoreController {

    FakeStoreDao dao = new FakeStoreDao();
    List<Product> products = dao.getAllProducts();

    @RequestMapping({ "/products", "/products/"})
    public List<Product> getAllProducts() {
        return products;
    }

    @RequestMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(required = false) int id) {
        Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Product not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @RequestMapping("/products/category/{category}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable(required = false) String category) {
        List<Product> tempProducts = products.stream().filter(p -> p.getCategory().equals(category)).toList();
        if (tempProducts.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Category not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tempProducts, HttpStatus.OK);
        }
    }

    @RequestMapping("/products/{id}/delete")
    public ResponseEntity<Object> deleteProductById(@PathVariable int id) {
        Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Product not found"), HttpStatus.NOT_FOUND);
        } else {
            products.remove(product);
            return new ResponseEntity<>(Collections.singletonMap("success", "Product deleted"), HttpStatus.OK);
        }
    }

    @RequestMapping("/products/add")
    public ResponseEntity<Object> addProduct(Product product) {
        if (product == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Product not found"), HttpStatus.NOT_FOUND);
        } else {
            products.add(product);
            return new ResponseEntity<>(Collections.singletonMap("success", "Product added"), HttpStatus.OK);
        }
    }

    @RequestMapping("/images/{image}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String image) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/images/" + image);
        if(!imgFile.exists()) {
            return ResponseEntity.notFound().build();
        } else {
            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }
    }

}
