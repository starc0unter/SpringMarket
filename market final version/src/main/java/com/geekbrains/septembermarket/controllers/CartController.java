package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.services.UserService;
import com.geekbrains.septembermarket.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductsService productsService;
    private UserService userService;
    private Cart cart;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String show(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByPhone(principal.getName());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("firstName", user.getFirstName());
        }
        model.addAttribute("items", cart.getItems().values());
        return "cart";
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productsService.findById(id);
        cart.addProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }
}
