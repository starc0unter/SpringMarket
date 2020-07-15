package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.Order;
import com.geekbrains.septembermarket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
