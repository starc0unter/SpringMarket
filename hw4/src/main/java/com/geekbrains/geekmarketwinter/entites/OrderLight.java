package com.geekbrains.geekmarketwinter.entites;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderLight {
    final Map<Long, Long> productId2Quantity = new HashMap<>();
    Long totalQuantity = 0L;

    public void addProductInfo(Long productId, Long quantity) {
        productId2Quantity.merge(productId, quantity, Long::sum);
        totalQuantity += quantity;
    }

    public Map<Long, Long> getProductId2Quantity() {
        return Collections.unmodifiableMap(productId2Quantity);
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }
}
