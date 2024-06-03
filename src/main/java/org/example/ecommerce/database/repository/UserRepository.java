package org.example.ecommerce.database.repository;

import org.example.ecommerce.database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username =: username ")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email =: email")
    User findByEmail(String email);
}
