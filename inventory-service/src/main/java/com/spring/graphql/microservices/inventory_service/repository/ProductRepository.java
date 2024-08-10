package com.spring.graphql.microservices.inventory_service.repository;

import com.spring.graphql.microservices.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}
