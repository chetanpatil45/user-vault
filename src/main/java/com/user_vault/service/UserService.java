package com.user_vault.service;

import com.user_vault.entity.User;

public interface UserService {
    public User authenticate(String email, String password);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public User getUserById(int id);
    public User getByEmail(String email);
    public boolean register(User user);
}
