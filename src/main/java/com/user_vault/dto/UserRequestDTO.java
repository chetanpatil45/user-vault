package com.user_vault.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String pass;
    private String phone;
    private String designation;
    private String gender;
    private String bio;
}
