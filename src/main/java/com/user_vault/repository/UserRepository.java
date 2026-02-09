package com.user_vault.repository;

import com.user_vault.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //custom repository for user.
    public User findByEmail(String email);

}
