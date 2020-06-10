package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @NotNull(message = "категория не выбрана")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "vendor_code")
    @NotNull(message = "не может быть пустым")
    @Pattern(regexp = "([0-9]{1,})", message = "недопустимый символ")
    @Size(min = 8, max = 8, message = "требуется 8 числовых символов")
    private String vendorCode;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<ProductImage> images;

    @Column(name = "title")
    @NotNull(message = "не может быть пустым")
    @Size(min = 5, max = 250, message = "требуется минимум 5 символов")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "price")
    @NotNull(message = "не может быть пустым")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    private double price;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    public void addImage(ProductImage productImage) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(productImage);
    }

    public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("category_id", "category");
        COLUMN_MAPPINGS.put("vendor_code", "vendorCode");
        COLUMN_MAPPINGS.put("images", "images");
        COLUMN_MAPPINGS.put("title", "title");
        COLUMN_MAPPINGS.put("short_description", "shortDescription");
        COLUMN_MAPPINGS.put("full_description", "fullDescription");
        COLUMN_MAPPINGS.put("price", "price");
        COLUMN_MAPPINGS.put("create_at", "createAt");
        COLUMN_MAPPINGS.put("update_at", "updateAt");
    }

    @Override
    public String toString() {
        return "Product title = '" + title + "'";
    }
}
