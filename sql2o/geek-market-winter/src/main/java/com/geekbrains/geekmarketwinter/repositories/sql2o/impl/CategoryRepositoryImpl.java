package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.CategoryRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class CategoryRepositoryImpl implements CategoryRepositorySql2o {

    private final Sql2o sql2o;

    private static final String SELECT_ALL_CATEGORIES = "select * from categories";

    @Autowired
    public CategoryRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Category> getAllCategories() {
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ALL_CATEGORIES, false)
                    .setColumnMappings(Category.COLUMN_MAPPINGS)
                    .executeAndFetch(Category.class);
        }
    }

}
