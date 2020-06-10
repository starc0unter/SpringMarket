package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.Product;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductRepositorySql2o {

    Product getByTitle(@NonNull String title);

    List<Product> getAll();

    Product getById(@NonNull Long id);

    void save(@NonNull Product product);

}
