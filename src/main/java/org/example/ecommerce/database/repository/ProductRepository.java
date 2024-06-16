package org.example.ecommerce.database.repository;

import org.example.ecommerce.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
