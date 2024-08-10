package com.spring.graphql.microservices.customer_service.service;

import com.spring.graphql.microservices.customer_service.client.InventoryClient;
import com.spring.graphql.microservices.customer_service.dto.Product;
import com.spring.graphql.microservices.customer_service.dto.ProductRequestDTO;
import com.spring.graphql.microservices.customer_service.dto.ProductStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private InventoryClient inventoryClient;

    public List<Product> getAllProducts() {
        return inventoryClient.getAllProducts();
    }

    public List<Product> retrieveProductBYCategory(String category) {
        return inventoryClient.viewProductsByCategory(category);
    }

    public Product receiveNewShipment(ProductRequestDTO productRequest) {
        return inventoryClient.receiveNewShipment(productRequest);
    }

    public Product updateStock(ProductStockDTO productStockDTO) {
        return inventoryClient.updateStock(productStockDTO);
    }
}
