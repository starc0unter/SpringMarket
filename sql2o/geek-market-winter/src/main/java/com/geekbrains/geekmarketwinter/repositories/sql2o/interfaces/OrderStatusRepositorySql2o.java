package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import org.springframework.lang.NonNull;

public interface OrderStatusRepositorySql2o {

    OrderStatus getStatusById(@NonNull Long id);

}
