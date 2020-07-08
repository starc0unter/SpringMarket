package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.OrderStatusRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class OrderStatusRepositoryImpl implements OrderStatusRepositorySql2o {
    private final Sql2o sql2o;

    private static final String GET_STATUSES = "select id, title from orders_statuses where id = :statusId";

    @Autowired
    public OrderStatusRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public OrderStatus getStatusById(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_STATUSES)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .addParameter("statusId", id)
                    .executeAndFetchFirst(OrderStatus.class);
        }
    }
}
