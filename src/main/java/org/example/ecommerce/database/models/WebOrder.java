package org.example.ecommerce.database.models;

import jakarta.persistence.*;

import java.util.List;

public record WebOrder(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @ManyToOne(optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        User user,

        @ManyToOne(optional = false)
        @JoinColumn(name = "address_id", nullable = false)
        Address address,

        @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
        List<WebOrderQuantities> quantities
) {
}
