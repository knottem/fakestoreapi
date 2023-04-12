package com.example.fakestoreapi.dao;

import com.example.fakestoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class FakeStoreDao {

    //decided to read the json file once to avoid reading it every time getAllProducts is called

    List<Product> products = readJson();

    public List<Product> getAllProducts() {
        return products;
    }

    private List<Product> readJson() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = null;
        try {
            products = mapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("products.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Product.class)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


}
