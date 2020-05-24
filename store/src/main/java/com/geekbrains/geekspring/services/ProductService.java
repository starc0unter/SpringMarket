package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(@NonNull ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(@NonNull Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product saveOrUpdate(@NonNull Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllByPriceBetween(@Nullable Long minPrice, @Nullable Long maxPrice, @NonNull Pageable pageable) {
        if (minPrice != null && maxPrice != null) {
            return productRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
        }
        if (minPrice != null) {
            return productRepository.findAllByPriceGreaterThanEqual(minPrice, pageable);
        }
        if (maxPrice != null) {
            return productRepository.findAllByPriceLessThanEqual(maxPrice, pageable);
        }
        return productRepository.findAll(pageable);
    }

}
