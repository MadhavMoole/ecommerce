package org.example.ecommerce.database.repository;

import org.example.ecommerce.database.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
    
}
