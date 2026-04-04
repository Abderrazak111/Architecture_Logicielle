package com.SOA.order_server.client;

import com.SOA.order_server.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-server")
public interface ProductClient {
    @GetMapping("/api/products/{id}") ProductDTO getProductById(@PathVariable Long id);
    @PutMapping("/api/products/{id}/stock")
    ProductDTO updateStock(@PathVariable Long id,
                           @RequestParam("quantity") Integer quantity);
}
