package com.flamexander.cloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("geek-spring-cloud-eureka-client")
public interface ProductClient {
    @GetMapping("/products")
    List<Product> getAllProducts();

    @PostMapping("/products")
    Product addNewProduct(@RequestBody Product product);
}
