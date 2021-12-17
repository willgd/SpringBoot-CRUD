package com.example.springbootcrud.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private int userId;
    private String name;
    private int age;
    private String email;
    private String gender;
    private String avatar;
    private String detail;
    private int level;
    private boolean admin;
    private String tel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
}
