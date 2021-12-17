package com.example.springbootcrud.mapper;

import com.example.springbootcrud.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public Admin getAdminByUserId(int userId);
    public List<Admin> getAllAdmin();
    public String getAdminAvatar(int userId);
}
