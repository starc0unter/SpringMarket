package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.repositories.entityframework.OrderStatusRepository;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.OrderStatusRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    private OrderStatusRepositorySql2o orderStatusRepository;

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepositorySql2o orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Nullable
    public OrderStatus getStatusById(Long id) {
        return orderStatusRepository.getStatusById(id);
    }
}
