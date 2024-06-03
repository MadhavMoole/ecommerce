package org.example.ecommerce.database.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public record Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @Column(name = "name", nullable = false)
        String name,

        @Column(name = "image", nullable = false)
        String image,

        @Column(name = "category", nullable = false)
        String category,

        @Column(name = "price", nullable = false)
        BigDecimal price,

        @Column(name = "description", nullable = false)
        String description,

        @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
        Inventory inventory
) {

}