package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.repositories.entityframework.DeliveryAddressRepository;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.DeliveryAddressRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
    private DeliveryAddressRepositorySql2o deliveryAddressRepository;

    @Autowired
    public void setDeliveryAddressRepository(DeliveryAddressRepositorySql2o deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepository.getUserAddresses(userId);
    }
}
