package com.example.springbootcrud.mapper;

import com.example.springbootcrud.pojo.UserLogin;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserLoginMapper {
    UserLogin login(UserLogin userLogin);
}
