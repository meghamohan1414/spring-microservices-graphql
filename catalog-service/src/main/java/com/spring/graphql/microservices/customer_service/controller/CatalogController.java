package com.spring.graphql.microservices.customer_service.controller;

import com.spring.graphql.microservices.customer_service.dto.ProductRequestDTO;
import com.spring.graphql.microservices.customer_service.dto.ProductStockDTO;
import com.spring.graphql.microservices.customer_service.service.CatalogService;
import com.spring.graphql.microservices.customer_service.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return catalogService.getAllProducts();
    }

    @GetMapping("/viewProductByCategory/category")
    public List<Product> viewProductByCategory(@RequestParam String category) {
        return catalogService.retrieveProductBYCategory(category);
    }

    @PostMapping("/shipment")
    public Product receiveNewShipment(@RequestBody ProductRequestDTO productRequest){
        return catalogService.receiveNewShipment(productRequest);
    }

    @PatchMapping("/shipment/stock")
    public Product updateStock(@RequestBody ProductStockDTO productStockDTO){
        return catalogService.updateStock(productStockDTO);
    }

}
