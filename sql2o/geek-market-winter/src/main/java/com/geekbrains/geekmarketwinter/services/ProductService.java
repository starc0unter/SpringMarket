package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.repositories.entityframework.ProductRepository;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.ProductRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductRepositorySql2o productRepositorySql2o;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductRepositorySql2o(ProductRepositorySql2o productRepositorySql2o) {
        this.productRepositorySql2o = productRepositorySql2o;
    }

    public List<Product> getAllProducts() {
        return productRepositorySql2o.getAll();
    }

    public List<Product> getAllProductsWithFilter(Specification<Product> productSpecs) {
        return productRepository.findAll(productSpecs);
    }

    public Page<Product> getAllProductsByPage(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Product getProductById(Long id) {
        return productRepositorySql2o.getById(id);
    }

    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

    public boolean isProductWithTitleExists(String productTitle) {
        return productRepositorySql2o.getByTitle(productTitle) != null;
    }

    public void saveProduct(Product product) {
        productRepositorySql2o.save(product);
    }
}
