package com.example.springbootcrud.mapper;

import com.example.springbootcrud.pojo.User;
import com.example.springbootcrud.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public User getUserByUserId(int userId);
    public List<User> getAllUser();
    public UserLogin getLoginUserByUserId(String username);
    public void updateUserByUserId(User user);
    public void deleteUserByUserId(int userId);
    public void insertUser(User user);
}
