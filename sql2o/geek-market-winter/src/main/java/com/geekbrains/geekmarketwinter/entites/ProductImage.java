package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products_images")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "path")
    private String path;

    public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("product_id", "product");
        COLUMN_MAPPINGS.put("path", "path");
    }
}
