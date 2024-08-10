package com.spring.graphql.microservices.inventory_service.controller;

import com.spring.graphql.microservices.inventory_service.entity.Product;
import com.spring.graphql.microservices.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<Product> getProducts(){
        return  productService.getProducts();
    }
    @QueryMapping
    public List<Product> getProductsByCategory(@Argument String category) {
        return productService.getProductByCategory(category);
    }

   @MutationMapping
    public Product updateStock(@Argument Long id, @Argument int stock){
        return productService.updateStock(id, stock);
    }

    @MutationMapping
    public Product receiveNewShipment(@Argument Long id, @Argument int quantity) {
        return productService.receiveNewShipment(id, quantity);
    }
}
