package com.geekbrains.septembermarket.repositories;

import com.geekbrains.septembermarket.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
