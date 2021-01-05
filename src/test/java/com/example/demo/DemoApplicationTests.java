package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Usermapper usermapper;

    @Test
    public void testFindAll(){
//        List<User> users = usermapper.selectList(null);
//        for (User user:users) {
//            System.out.println(user);
//        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","Jone");
        User user = usermapper.selectOne(queryWrapper);
        System.out.println(user);

    }


}
