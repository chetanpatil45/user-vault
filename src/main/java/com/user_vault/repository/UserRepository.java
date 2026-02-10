package com.user_vault.repository;

import com.user_vault.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //custom repository for user.
    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);
}
