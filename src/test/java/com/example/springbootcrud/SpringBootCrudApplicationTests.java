package com.example.springbootcrud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.LocalTime.now;

@SpringBootTest
class SpringBootCrudApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void sdasd(){
        System.out.println(now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();

        System.out.println(simpleDateFormat.format(date));
    }
}
