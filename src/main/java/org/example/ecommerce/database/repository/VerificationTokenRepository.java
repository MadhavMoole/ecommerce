package org.example.ecommerce.database.repository;

import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
    Optional<VerificationToken> findByToken(String token);
    void deleteByUser(User user);
}
