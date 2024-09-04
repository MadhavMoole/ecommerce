package org.example.ecommerce.database.repository;

import java.util.List;

import org.example.ecommerce.database.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    List<Address> findByUserId(Long id);
}
