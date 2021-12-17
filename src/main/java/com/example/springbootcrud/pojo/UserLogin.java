package com.example.springbootcrud.pojo;

import lombok.Data;

@Data
public class UserLogin {
    private String username;
    private String password;
    private int userId;
}
