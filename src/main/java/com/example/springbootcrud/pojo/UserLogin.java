package com.example.springbootcrud.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class UserLogin {
    private String username;
    private String password;
    private int userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
