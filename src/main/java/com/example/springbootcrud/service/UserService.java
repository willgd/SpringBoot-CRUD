package com.example.springbootcrud.service;

import com.example.springbootcrud.pojo.Admin;
import com.example.springbootcrud.pojo.User;
import com.example.springbootcrud.pojo.UserLogin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    public User getUserByUserId(int userId);
    public PageInfo<User> getAllUser(int pageNum, int pageSize);
    public Admin getAdminByUserId(int userId);
    public List<Admin> getAllAdmin();
    public void updateUserByUserId(User user);
    public void deleteUserByUserId(int userId);
    public void insertUser(User user);
    public UserLogin login(UserLogin userLogin);

}
