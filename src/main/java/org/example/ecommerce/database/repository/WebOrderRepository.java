package org.example.ecommerce.database.repository;

import org.example.ecommerce.database.models.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {

    List<WebOrder> findOrderByUserId(Long userId);
}

