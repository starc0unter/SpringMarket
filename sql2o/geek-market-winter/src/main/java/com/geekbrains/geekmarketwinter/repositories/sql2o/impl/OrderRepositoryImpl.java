package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.OrderRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class OrderRepositoryImpl implements OrderRepositorySql2o {
    private final Sql2o sql2o;

    private static final String GET_ALL_ORDERS = "select id, status, price, delivery_price, phone_number, delivery_date, create_at, update_at from orders";
    private static final String SELECT_USER_QUERY = "select id, username, password, first_name, last_name, email from users where username = :u_name";
    private static final String GET_ORDER_BY_ID = "select id, status, price, delivery_price, phone_number, delivery_date, create_at, update_at from orders where id = :orderId";
    private static final String SAVE_ORDER = "insert into orders (user_id, order, status_id, price, delivery_price, delivery_address_id, phone_number, delivery_date, create_at, update_at)" +
            "VALUES (:user_id, :order, :status_id, :price, :delivery_price, :delivery_address_id, :phone_number, :delivery_date, :create_at, :update_at)";
    private static final String SAVE_ORDER_ITEM = "insert into orders_item (quantity, item_price, total_price, product_id, order_id)" +
            "VALUES (:quantity, :item_price, :total_price, :product_id, :order_id)";


    @Autowired
    public OrderRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Order> getAllOrders() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_ALL_ORDERS)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetch(Order.class);
        }
    }

    @Override
    public Order getById(Long orderId) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_ORDER_BY_ID)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .addParameter("orderId", orderId)
                    .executeAndFetchFirst(Order.class);
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = sql2o.open()) {
            final Long id = connection.createQuery(SAVE_ORDER, true)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .addParameter("user_id", order.getUser().getId())
                    .addParameter("status_id", order.getStatus().getClass())
                    .addParameter("price", order.getPrice())
                    .addParameter("delivery_price", order.getDeliveryPrice())
                    .addParameter("delivery_address_id", order.getDeliveryAddress())
                    .addParameter("phone_number", order.getPhoneNumber())
                    .addParameter("delivery_date", order.getDeliveryDate())
                    .addParameter("create_at", order.getCreateAt())
                    .addParameter("update_at", order.getUpdateAt())
                    .executeUpdate()
                    .getKey(Long.class);

            order.getOrderItems().forEach(item -> setOrderItem(item, connection, id));
        }
    }

    private void setOrderItem(@NonNull OrderItem orderItem, @NonNull Connection connection, @NonNull Long orderId) {
        connection.createQuery(SAVE_ORDER_ITEM)
                .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                .addParameter("quantity", orderItem.getQuantity())
                .addParameter("item_price", orderItem.getItemPrice())
                .addParameter("total_price", orderItem.getTotalPrice())
                .addParameter("product_id", orderItem.getProduct().getId())
                .addParameter("order_id", orderId)
                .executeUpdate();
    }

}
