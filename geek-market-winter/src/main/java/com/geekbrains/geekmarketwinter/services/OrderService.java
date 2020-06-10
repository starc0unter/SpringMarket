package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.repositories.OrderItemRepository;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.repositories.OrderStatusRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findAllByUser(user).orElse(Collections.emptyList());
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order makeOrder(ShoppingCart shoppingCart, String phoneNumber, DeliveryAddress deliveryAddress, User user) {
        Order order = new Order();
        shoppingCart.getItems().forEach(item -> item.setOrder(order));
        shoppingCart.getItems().forEach(item -> item.setId(null));
        order.setOrderItems(shoppingCart.getItems());
        order.setUser(user);
        order.setPrice(shoppingCart.getTotalCost());
        order.setDeliveryAddress(deliveryAddress);
        order.setPhoneNumber(phoneNumber);
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setStatus(findStatus("Сформирован"));
        order.setDeliveryPrice(0.0);
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        return save(order);
    }

    public Order initOrder(ShoppingCart shoppingCart, User user) {
        Order order = new Order();
        order.setOrderItems(shoppingCart.getItems());
        order.setUser(user);
        order.setPrice(shoppingCart.getTotalCost());

        return order;
    }

    public OrderStatus findStatus(String title) {
        return orderStatusRepository.findByTitle(title);
    }
}
