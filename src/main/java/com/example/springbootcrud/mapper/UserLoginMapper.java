package com.example.springbootcrud.mapper;

import com.example.springbootcrud.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {
    UserLogin login(UserLogin userLogin);
}
