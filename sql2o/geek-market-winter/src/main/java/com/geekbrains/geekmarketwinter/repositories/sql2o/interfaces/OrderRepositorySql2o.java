package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.Order;
import org.springframework.lang.NonNull;

import java.util.List;

public interface OrderRepositorySql2o {
    @NonNull
    List<Order> getAllOrders();

    @NonNull
    Order getById(@NonNull Long orderId);

    void save(@NonNull Order order);
}
