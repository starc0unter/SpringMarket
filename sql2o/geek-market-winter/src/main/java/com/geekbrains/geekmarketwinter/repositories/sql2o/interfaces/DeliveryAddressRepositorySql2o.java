package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeliveryAddressRepositorySql2o {
    List<DeliveryAddress> getUserAddresses(@NonNull Long userId);
}
