package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Usermapper usermapper;

    @Test
    public void testFindAll(){
        List<User> users = usermapper.selectList(null);
        for (User user:users) {
            System.out.println(user);
        }

    }


}
