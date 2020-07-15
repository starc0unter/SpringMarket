package com.geekbrains.septembermarket.services;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.OrderRepository;
import com.geekbrains.septembermarket.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private Cart cart;

    @Autowired
    public OrderService(OrderRepository orderRepository, Cart cart) {
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public Order createOrder(User user, String phone, String address) {
        Order order = new Order(user, phone, address);
        cart.getItems().values().stream().forEach(i -> order.addItem(i));
        cart.clear();
        return orderRepository.save(order);
    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }
}
