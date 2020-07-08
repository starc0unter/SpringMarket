package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.repositories.entityframework.CategoryRepository;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.CategoryRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepositorySql2o categoryRepositorySql2o;

    @Autowired
    public void setCategoryRepositorySql2o(CategoryRepositorySql2o categoryRepositorySql2o) {
        this.categoryRepositorySql2o = categoryRepositorySql2o;
    }

    public List<Category> getAllCategories() {
        return categoryRepositorySql2o.getAllCategories();
    }
}
