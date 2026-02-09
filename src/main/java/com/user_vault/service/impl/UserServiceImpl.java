package com.user_vault.service.impl;

import com.user_vault.entity.User;
import com.user_vault.repository.UserRepository;
import com.user_vault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User authenticate(String email, String password) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
