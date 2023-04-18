package com.example.fakestoreapi.dao;


import com.example.fakestoreapi.controller.FakeStoreController;
import com.example.fakestoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



public class FakeStoreDao {

    public List<Product> getAllProducts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = null;
        try {
            products = mapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("products.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Product.class)
            );
            for (Product p : products) {
                p.setImage(linkTo(methodOn(FakeStoreController.class).getImage(p.getImage())).withRel("image").getHref());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}
