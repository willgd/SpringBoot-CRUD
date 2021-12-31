package com.example.springbootcrud.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserInfo {
    private User User;
    private UserLogin userLogin;
    private Admin admin;
    private int Code;
}
