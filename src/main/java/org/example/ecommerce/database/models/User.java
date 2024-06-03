package org.example.ecommerce.database.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        String id,

        @Column(nullable = false, name = "username")
        String username,

        @Column(nullable = false, name = "password")
        String password,

        @Column(nullable = false, name = "email")
        String email,

        @Column(nullable = false, name = "first_name")
        String firstName,

        @Column(nullable = false, name = "last_name")
        String lastName,

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
        List<Address> addresses
) {

}
