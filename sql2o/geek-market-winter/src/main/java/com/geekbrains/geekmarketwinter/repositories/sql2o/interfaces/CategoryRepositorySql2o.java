package com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces;

import com.geekbrains.geekmarketwinter.entites.Category;

import java.util.List;

public interface CategoryRepositorySql2o {
    List<Category> getAllCategories();
}
