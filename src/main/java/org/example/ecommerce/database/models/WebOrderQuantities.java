package org.example.ecommerce.database.models;

import jakarta.persistence.*;

public record WebOrderQuantities(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @ManyToOne(optional = false)
        @JoinColumn(name = "product_id", nullable = false)
        Product product,

        @Column(name = "quantity", nullable = false)
        Integer quantity,

        @ManyToOne(optional = false)
        @JoinColumn(name = "order_id", nullable = false)
        WebOrder order
) {
}
