package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.services.ProductService;
import com.geekbrains.geekspring.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    // https://getbootstrap.com/docs/4.1/getting-started/introduction/csrf

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }
}
