package com.example.fakestoreapi.dao;


import com.example.fakestoreapi.controller.FakeStoreController;
import com.example.fakestoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



public class FakeStoreDao {

    private List<Product> getAllProducts() {
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

    public List<Product> getAllProductsWithLinks() {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            try {
                Link imageLink = linkTo(methodOn(FakeStoreController.class).getImage(product.getImage())).withRel("image");
                product.setImage(imageLink.getHref());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return products;
    }

}
