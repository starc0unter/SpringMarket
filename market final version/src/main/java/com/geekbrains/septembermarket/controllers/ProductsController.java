package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.repositories.specifications.ProductSpecifications;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.utils.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product = null;
        if (id != null) {
            product = productsService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}") // todo починить куки (видимость)
    public Product showProduct(@PathVariable(name = "id") Long id,
                               @CookieValue(value = "lastProducts", required = false) String lastProducts,
                               HttpServletResponse response
    ) {
        Product product = productsService.findById(id);
        if (lastProducts == null) {
            lastProducts = String.valueOf(product.getId());
            response.addCookie(new Cookie("lastProducts", lastProducts));
        } else {
            lastProducts = lastProducts + "q" + product.getId();
            response.addCookie(new Cookie("lastProducts", lastProducts));
        }
        System.out.println(lastProducts);
        return product;
    }
}
