package com.spring.graphql.microservices.inventory_service.service;

import com.spring.graphql.microservices.inventory_service.entity.Product;
import com.spring.graphql.microservices.inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }
    public List<Product> getProductByCategory(String category) {
        return repo.findByCategory(category);
    }

    //Mutation
    public Product updateStock(Long id, int stock) {
        Product existingProduct = repo.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
        existingProduct.setStock(stock);
        return repo.save(existingProduct);
    }

    public Product receiveNewShipment(Long id, int quantity) {
        Product existingProduct = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id: "+id));
        existingProduct.setStock(existingProduct.getStock()+quantity);
        return repo.save(existingProduct);
    }
}
