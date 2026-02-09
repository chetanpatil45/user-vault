package com.user_vault.mapper;

import com.user_vault.dto.UserDTO;
import com.user_vault.dto.UserRequestDTO;
import com.user_vault.entity.User;

public class UserMapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDesignation(),
                user.getGender(),
                user.getBio()
        );
    }

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPass(dto.getPass());
        user.setPhone(dto.getPhone());
        user.setDesignation(dto.getDesignation());
        user.setGender(dto.getGender());
        user.setBio(dto.getBio());
        return user;
    }
}
