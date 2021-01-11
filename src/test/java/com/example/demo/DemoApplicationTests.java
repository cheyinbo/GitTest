package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Usermapper usermapper;

    @Test
    public void testMybatisPlus(){
//        List<User> users = usermapper.selectList(null);
//        for (User user:users) {
//            System.out.println(user);
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name","zhangsan");
//        User user = usermapper.selectOne(queryWrapper);
//
//        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
//        userUpdateWrapper.set("name","cheyinbo");
//        userUpdateWrapper.eq("id",user.getId());
//        usermapper.update(user,userUpdateWrapper);

        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        IPage<User> userIPage = usermapper.selectPage(page,queryWrapper);
        System.out.println(userIPage);

    }

    @Test
    public void testM() throws FileNotFoundException {
        System.out.println(ResourceUtils.getURL("classpath:").getPath());

    }


}
