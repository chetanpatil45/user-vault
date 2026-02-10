package com.user_vault.service.impl;

import com.user_vault.entity.User;
import com.user_vault.repository.UserRepository;
import com.user_vault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User authenticate(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user != null && encoder.matches(password, user.getPass())){
            return user;
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null || user.getId() < 1) return false;

        Optional<User> optionalUser = repository.findById(user.getId());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(user.getName());
            existingUser.setDesignation(user.getDesignation());
            existingUser.setGender(user.getGender());
            existingUser.setBio(user.getBio());
            existingUser.setPhone(user.getPhone());

            repository.save(existingUser);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteUser(User user) {
        User validUser = repository.findById(user.getId()).orElse(null);
        if (validUser != null){
            repository.delete(validUser);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean register(User user) {
        if (user == null || user.getPass() == null) return false;

        if (repository.existsByEmail(user.getEmail())) {
            return false; // or throw exception
        }

        user.setPass(encoder.encode(user.getPass()));
        repository.save(user);
        return true;
    }

    @Override
    public boolean validatePass(int id, String pass) {
        User user = repository.findById(id).orElse(null);
        return user != null && encoder.matches(pass, user.getPass());
    }

    @Override
    public boolean updatePassword(int id, String password) {
        if (id <= 0 || password == null) return false;

        User user = repository.findById(id).orElse(null);
        if (user != null){
            user.setPass(encoder.encode(password));
            repository.save(user);
            return true;
        }
        return false;
    }

}
