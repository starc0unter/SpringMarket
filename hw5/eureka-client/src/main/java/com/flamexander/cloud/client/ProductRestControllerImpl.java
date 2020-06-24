package com.flamexander.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestControllerImpl implements ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productPageService) {
        this.productService = productPageService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}