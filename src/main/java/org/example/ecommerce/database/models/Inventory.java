package org.example.ecommerce.database.models;

import jakarta.persistence.*;

public record Inventory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @OneToOne(optional = false, orphanRemoval = true)
        @JoinColumn(name = "product_id", nullable = false, unique = true)
        Product product,

        @Column(name = "quantity", nullable = false)
        Integer quantity
) {
}
