package org.example.ecommerce.database.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public record Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @ManyToOne(optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        User user,

        @Column(name = "address_line_1", nullable = false, length = 512)
        String addressLine1,

        @Column(name = "address_line_2", length = 512)
        String addressLine2,

        @Column(name = "city", nullable = false)
        String city,

        @Column(name = "country", nullable = false, length = 75)
        String country
) {
}
