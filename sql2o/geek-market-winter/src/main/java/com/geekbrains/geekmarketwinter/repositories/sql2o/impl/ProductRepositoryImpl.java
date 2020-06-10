package com.geekbrains.geekmarketwinter.repositories.sql2o.impl;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductImage;
import com.geekbrains.geekmarketwinter.repositories.sql2o.interfaces.ProductRepositorySql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.awt.image.ImageProducer;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepositorySql2o {
    private final Sql2o sql2o;

    private static final String GET_BY_TITLE = "select id, category_id, vendor_code, title, short_description, full_description, price, create_at, update_at from products where title = :title";
    private static final String GET_ALL = "select id, category_id, vendor_code, title, short_description, full_description, price, create_at, update_at from products";
    private static final String GET_ALL_BY_ID = "select id, category_id, vendor_code, title, short_description, full_description, price, create_at, update_at from products where id = :id";
    private static final String GET_IMAGES_BY_ID = "select id, path from products_images where product_id = :id";
    private static final String SAVE_PRODUCT = "insert into products (category_id, vendor_code, title, short_description, full_description, price, create_at, update_at)" +
            "values (:category_id, :vendor_code, :title, :short_description, :full_description, :price, :create_at, :update_at)";
    private static final String SAVE_PRODUCT_IMAGE = "insert into products_images (product_id, path) values (:product_id, :path)";


    @Autowired
    public ProductRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Product getByTitle(String title) {
        try (Connection connection = sql2o.open()) {
            final Product product = connection.createQuery(GET_BY_TITLE)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .addParameter("title", title)
                    .executeAndFetchFirst(Product.class);

            return setupImages(connection, product);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = sql2o.open()) {
            final List<Product> products = connection.createQuery(GET_ALL)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class);
            products.forEach(p -> setupImages(connection, p));
            return products;
        }
    }

    private Product setupImages(@NonNull Connection connection, @NonNull Product product) {
        final List<ProductImage> images = connection.createQuery(GET_IMAGES_BY_ID)
                .setColumnMappings(ProductImage.COLUMN_MAPPINGS)
                .addParameter("id", product.getId())
                .executeAndFetch(ProductImage.class);
        product.setImages(images);
        images.forEach(i -> i.setProduct(product));
        return product;
    }

    @Override
    public Product getById(Long id) {
        try (Connection connection = sql2o.open()) {
            final Product product = connection.createQuery(GET_ALL_BY_ID)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Product.class);

            setupImages(connection, product);
            return product;
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = sql2o.open()) {
            final Long id = connection.createQuery(SAVE_PRODUCT, true)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .addParameter("category_id", product.getCategory().getId())
                    .addParameter("vendor_code", product.getVendorCode())
                    .addParameter("title", product.getTitle())
                    .addParameter("short_description", product.getShortDescription())
                    .addParameter("full_description", product.getFullDescription())
                    .addParameter("price", product.getPrice())
                    .addParameter("create_at", product.getCreateAt())
                    .addParameter("update_at", product.getUpdateAt())
                    .executeUpdate()
                    .getKey(Long.class);

            product.getImages().forEach(image -> save(connection, id, image));

        }
    }

    private void save(@NonNull Connection connection, @NonNull Long productId, @NonNull ProductImage productImage) {
        connection.createQuery(SAVE_PRODUCT_IMAGE)
                .setColumnMappings(ProductImage.COLUMN_MAPPINGS)
                .addParameter("product_id", productId)
                .addParameter("path", productImage.getPath())
                .executeUpdate();
    }
}
