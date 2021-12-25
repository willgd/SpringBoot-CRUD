package com.example.springbootcrud.serviceImpl;

import com.example.springbootcrud.mapper.AdminMapper;
import com.example.springbootcrud.mapper.UserLoginMapper;
import com.example.springbootcrud.mapper.UserMapper;
import com.example.springbootcrud.pojo.Admin;
import com.example.springbootcrud.pojo.User;
import com.example.springbootcrud.pojo.UserLogin;
import com.example.springbootcrud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserLoginMapper userLoginMapper;


    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(int userId) {
        return userMapper.getUserByUserId(userId);
    }

    /**
     * 查询所以用户
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 根据用户id查找所对应的管理员账户
     * @param userId
     * @return
     */
    @Override
    public Admin getAdminByUserId(int userId) {
        Admin admin = adminMapper.getAdminByUserId(userId);
        String avatar = adminMapper.getAdminAvatar(admin.getUserId());
        admin.setAvatar(avatar);
        return admin;
    }

    /**
     * 查询所有管理员账户
     * @return
     */
    @Transactional
    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> adminList = adminMapper.getAllAdmin();
        for(Admin admin:adminList){
            String avatar = adminMapper.getAdminAvatar(admin.getUserId());
            admin.setAvatar(avatar);
        }
        log.info(String.valueOf(adminList));
        return adminList;
    }

    /**
     * 根据userId更新用户资料
     * @param user
     */
    @Transactional
    @Override
    public void updateUserByUserId(User user) {
        log.info("user={}",user);
        if (user.isAdmin()){
            userMapper.updateUserByUserId(user);
            adminMapper.updateAdmin(new Date(),user.getUserId());
        }else {
            userMapper.updateUserByUserId(user);
        }
    }

    /**
     * 根据userId删除用户
     * @param userId
     */
    @Transactional
    @Override
    public void deleteUserByUserId(int userId) {
        userMapper.deleteUserByUserId(userId);
    }

    /**
     * 添加用户
     * @param user
     */
    @Transactional
    @Override
    public void insertUser(User user) {
        if (user.isAdmin()) {
            userMapper.insertUser(user);
            adminMapper.insertAdmin(user.getUserId(), user.getUserId(), new Date(),new Date());
        }else {
            userMapper.insertUser(user);
        }
    }

    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public UserLogin login(UserLogin userLogin) {
        return userLoginMapper.login(userLogin);
    }
}
