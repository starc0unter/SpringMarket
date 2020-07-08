package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.DeliveryAddressRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class DeliveryAddressRepositoryImpl implements DeliveryAddressRepositorySql2o {
    private final Sql2o sql2o;

    private static final String GET_USER_ADDRESSES = "select id, user_id, address from delivery_addresses where user_id = :userId";

    @Autowired
    public DeliveryAddressRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<DeliveryAddress> getUserAddresses(@NonNull Long userId) {
        try (Connection connection = sql2o.open()) {
            //осознанно не заполняем юзера. Нужно либо хранить ссылку на юзера в бд, либо соединять зависимости в сущностях
            //чтобы ссылка на адреса была и у пользователя
            return connection.createQuery(GET_USER_ADDRESSES, false)
                    .setColumnMappings(DeliveryAddress.COLUMN_MAPPINGS)
                    .addParameter("userId", userId)
                    .executeAndFetch(DeliveryAddress.class);
        }
    }
}
