package com.flamexander.cloud.client.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductClient productClient;

    @Autowired
    public void setProductClient(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping(value = {"/products"})
    public String showProducts(Model model) {
        model.addAttribute("products", productClient.getAllProducts());
        return "index";
    }

    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @PostMapping(value = {"/product/add"})
    public String addProduct(@ModelAttribute("product") Product product) {
        productClient.addNewProduct(product);
        return "index";
    }
}
