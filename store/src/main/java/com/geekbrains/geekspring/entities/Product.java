package com.geekbrains.geekspring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    private enum Unit {
        Piece,
        Kilogram,
        gram,
        milliliter
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "short_info")
    private String shortInfo;

    @Column(name = "full_info")
    private String fullInfo;

    @Column(name = "price")
    private long price;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private Unit unit;
}
