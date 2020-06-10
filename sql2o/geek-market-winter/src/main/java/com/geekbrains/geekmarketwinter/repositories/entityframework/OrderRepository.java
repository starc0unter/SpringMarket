package com.geekbrains.geekmarketwinter.repositories.entityframework;

import com.geekbrains.geekmarketwinter.entites.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
