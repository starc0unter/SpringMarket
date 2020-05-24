package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ProductRepository extends JpaRepository<Product, Long>  {

    Page<Product> findAllByPriceGreaterThanEqual(@NonNull Long price, @NonNull Pageable pageable);

    Page<Product> findAllByPriceLessThanEqual(@NonNull Long target, @NonNull Pageable pageable);

    Page<Product> findAllByPriceBetween(@NonNull Long floor, @NonNull Long ceil, @NonNull Pageable pageable);

}
