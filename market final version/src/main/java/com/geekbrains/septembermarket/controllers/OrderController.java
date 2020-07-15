package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.UserRepository;
import com.geekbrains.septembermarket.services.MailService;
import com.geekbrains.septembermarket.services.OrderService;
import com.geekbrains.septembermarket.services.UserService;
import com.geekbrains.septembermarket.utils.Cart;
import com.geekbrains.septembermarket.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/create")
    public String createOrder(Principal principal,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "address") String address) {
        User user = null;
        if (principal != null) {
            user = userService.findByPhone(principal.getName());
        } else {
            if (userService.isUserExist(phone)) {
                user = userService.findByPhone(phone);
            } else {
                SystemUser systemUser = new SystemUser();
                systemUser.setPhone(phone);
                systemUser.setFirstName(firstName);
                user = userService.save(systemUser);
            }
        }
        Order order = orderService.createOrder(user, phone, address);
//        todo: enable email
//        if (user.getEmail() != null) {
//            mailService.sendOrderMail(order);
//        }
        return "redirect:/paypal/buy/" + order.getId();
    }

    @GetMapping("/history")
    public String showHistory(Principal principal, Model model) {
        User user = userService.findByPhone(principal.getName());
        List<Order> userOrders = orderService.findAllByUser(user);
        model.addAttribute("orders", userOrders);
        return "orders-history";
    }
}