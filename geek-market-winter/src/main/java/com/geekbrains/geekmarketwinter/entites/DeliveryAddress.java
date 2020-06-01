package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery_addresses")
@Data
@NoArgsConstructor
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public DeliveryAddress(User user, String address) {
        this.user = user;
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address")
    private String address;
}
