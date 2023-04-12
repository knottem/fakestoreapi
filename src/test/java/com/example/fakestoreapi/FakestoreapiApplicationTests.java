package com.example.fakestoreapi;

import com.example.fakestoreapi.dao.FakeStoreDao;
import com.example.fakestoreapi.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FakestoreapiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getAllProducts() {
        FakeStoreDao dao = new FakeStoreDao();
        List<Product> products = dao.getAllProductsWithLinks();

        Optional<Product> product1 = products.stream().filter(p -> p.getId() == 1).findFirst();
        assertTrue(product1.isPresent());
        assertEquals(109.95, product1.get().getPrice());

        Optional<Product> product2 = products.stream().filter(p -> p.getId() == 2).findFirst();
        assertTrue(product2.isPresent()); //
        assertEquals(22.3, product2.get().getPrice());

        Optional<Product> product3 = products.stream().filter(p -> p.getId() == 3).findFirst();
        assertTrue(product3.isPresent());
        assertEquals(4.7, product3.get().getRating().getRate());
    }

}
