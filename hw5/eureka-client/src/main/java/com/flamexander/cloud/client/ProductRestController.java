package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductRestController {
    @GetMapping("/products")
    List<Product> getAllProducts();

    @PostMapping("/products")
    Product addNewProduct(@RequestBody Product product);
}
