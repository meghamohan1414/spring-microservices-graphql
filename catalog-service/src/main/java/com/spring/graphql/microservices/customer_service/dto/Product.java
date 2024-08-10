package com.spring.graphql.microservices.customer_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Product {
    private Integer id;
    private String name;
    private String category;
    private Float price;
    private Integer stock;
}
