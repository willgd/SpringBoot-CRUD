package com.example.springbootcrud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.LocalTime.now;

@SpringBootTest
class SpringBootCrudApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void redis(){
        Jedis jedis = new Jedis("172.31.33.117", 6379);
        System.out.println("redis running"+jedis.ping());
        jedis.set("name","zhang3");
        System.out.println(jedis.get("name"));
    }
}
