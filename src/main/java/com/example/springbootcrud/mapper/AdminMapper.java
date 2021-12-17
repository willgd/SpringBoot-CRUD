package com.example.springbootcrud.mapper;

import com.example.springbootcrud.pojo.Admin;
import com.example.springbootcrud.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AdminMapper {
    public Admin getAdminByUserId(int userId);
    public List<Admin> getAllAdmin();
    public String getAdminAvatar(int userId);
    public void insertAdmin(@Param("adminId")int adminId,@Param("userId")int userId ,@Param("creatTime") Date creatTime,@Param("updateTime") Date updateTime);
    public void updateAdmin(@Param("updateTime")Date updateTime,@Param("userId")int userId);
}
