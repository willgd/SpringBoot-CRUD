package com.example.springbootcrud;

import com.example.springbootcrud.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.LocalTime.now;
@Slf4j
@SpringBootTest
class SpringBootCrudApplicationTests {
    @Test
    void contextLoads() {
    }
    @Resource
    UserServiceImpl userService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 测试查询方法
     */
    @Test
    void testGetUserByUserId(){
        log.info("getUserByUserId查询到结果为：={}",userService.getUserByUserId(10001));
    }

    /**
     * 测试查询所有用户方法
     */
    @Test
    void testGetAllUser(){
        log.info("GetAllUser查询到结果为：={}",userService.getAllUser());
    }

    /**
     * 测试查询管理员方法
     */
    @Test
    void testGetAdminByUserId(){
        log.info("GetAdminByUserId查询到结果为：={}",userService.getAdminByUserId(10001));
    }

    /**
     * 测试查询所有管理员方法
     */
    @Test
    void testGetAllAdmin(){
        log.info("GetAllAdmin查询到结果为：={}",userService.getAllAdmin());
    }
    /**
     * 测试redis
     */
    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("name","zhangsan");
        String name = operations.get("name");
        log.info("name={},redisConnectionFactory={}",name,redisConnectionFactory.getClass());
    }
}
