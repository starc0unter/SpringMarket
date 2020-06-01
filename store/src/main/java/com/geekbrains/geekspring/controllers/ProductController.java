package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    private ProductService productService;
    public static final int MIN_PRODUCTS_IN_PAGE = 10;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showMain(Model model,
                           @RequestParam(value = "page", required = false) @Nullable Integer page,
                           @RequestParam(value = "pageSize", required = false) @Nullable Integer pageSize,
                           @RequestParam(value = "minPrice", required = false) @Nullable Long minPrice,
                           @RequestParam(value = "maxPrice", required = false) @Nullable Long maxPrice) {

        model.addAttribute("activeTab", "Products");
        model.addAttribute("products", productService.findAllByPriceBetween(
                minPrice, maxPrice,
                PageRequest.of(
                        page == null ? 0 : page - 1,
                        pageSize == null ? MIN_PRODUCTS_IN_PAGE : pageSize
                )
        ));
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }

    @GetMapping("/modify/{id}")
    public String modifyProduct(Model model, @PathVariable(value = "id") Long productId) {
        Product product = productService.findProductById(productId).orElse(null);
        if (product == null) return "products";

        model.addAttribute("product", product);
        return "product_add";
    }

    @PostMapping("/modify")
    public String modifyProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product_add";
    }

    @PostMapping(value = {"/add"})
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/";
    }

}
