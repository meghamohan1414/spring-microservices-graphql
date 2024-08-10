package com.spring.graphql.microservices.customer_service.client;

import com.spring.graphql.microservices.customer_service.dto.Product;
import com.spring.graphql.microservices.customer_service.dto.ProductRequestDTO;
import com.spring.graphql.microservices.customer_service.dto.ProductStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InventoryClient {
   private String url="http://localhost:9090";

    @Autowired
    private HttpGraphQlClient graphQlClient;

    public List<Product> getAllProducts() {

      String query = "query GetProducts{\n" +
       "  getProducts {\n" +
       "    category\n" +
       "    name\n" +
       "    id\n" +
       "    price\n" +
       "    stock\n" +
       "  }\n" +
       "}";

      return graphQlClient.document(query)
              .retrieve("getProducts")
              .toEntityList(Product.class).block();

   }

   public List<Product> viewProductsByCategory(String category) {
        String queryViewProductsByCategory = String.format("query viewProductByCategory {\n" +
                "  getProductsByCategory(category: \"%s\") {\n" +
                "    category\n" +
                "    name\n" +
                "    price\n" +
                "    stock\n" +
                "  }\n" +
                "}", category);
        return  graphQlClient.document(queryViewProductsByCategory)
                .retrieve("getProductsByCategory")
                .toEntityList(Product.class).block();
   }

    public Product receiveNewShipment(ProductRequestDTO productRequest) {
        String queryReceiveNewShipment = String.format("mutation ReceiveNewShipment {\n" +
                "  receiveNewShipment(id: \"%s\", quantity: %d) {\n" +
                "    id\n" +
                "    name\n" +
                "    category\n" +
                "    stock\n" +
                "  }\n" +
                "}", productRequest.getId(), productRequest.getQuantity());

        return graphQlClient.document(queryReceiveNewShipment)
                .retrieve("receiveNewShipment")
                .toEntity(Product.class).block();
    }

    public Product updateStock(ProductStockDTO productStockDTO) {
        String queryUpdateStock = String.format("mutation UpdateStock {\n" +
                "  updateStock(id: \"%d\", stock: %d) {\n" +
                "    name\n" +
                "    stock\n" +
                "    price\n" +
                "  }\n" +
                "}", productStockDTO.getId(), productStockDTO.getStock());
        return graphQlClient.document(queryUpdateStock)
                .retrieve("updateStock")
                .toEntity(Product.class).block();
    }
}
