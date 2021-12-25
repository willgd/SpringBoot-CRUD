package com.example.springbootcrud.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLogin {
    private String username;
    private String password;
    private int userId;
}
