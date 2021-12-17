package com.example.springbootcrud.service;

import com.example.springbootcrud.pojo.Admin;
import com.example.springbootcrud.pojo.User;

import java.util.List;

public interface UserService {
    public User getUserByUserId(int userId);
    public List<User> getAllUser();
    public Admin getAdminByUserId(int userId);
    public List<Admin> getAllAdmin();
    public void updateUserByUserId(User user);
    public void deleteUserByUserId(int userId);
    public void insertUser(User user);
}
