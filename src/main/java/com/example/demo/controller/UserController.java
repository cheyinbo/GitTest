package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtsUtils_02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @GetMapping("/login")
    public Map<String,Object> login(@RequestParam String name,String password){
        Assert.notNull(name,"用户名为空");
        Assert.notNull(password,"密码为空");

        HashMap<String,Object> maps = null;
        try{
            maps = new HashMap<>();

            User user = userService.findUserInfo(name, DigestUtils.md5DigestAsHex(password.getBytes()));
            String token;
            if(user != null){
                token = JwtsUtils_02.getToken(user);
                maps.put("token",token);
                user.setPassword("不可见");
                redisTemplate.opsForValue().set(token, user);
                redisTemplate.expire(token,24, TimeUnit.HOURS);
            }
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return maps;
    }

    @GetMapping("/findUserInfo")
    public Map<String,Object> findUserInfo(@RequestParam String token){
        HashMap<String,Object> maps = new HashMap<>();
        User user = (User) redisTemplate.opsForValue().get(token);
        maps.put("user",user);
        return maps;
    }

    @GetMapping("/findAllUsers")
    public List<User> findAllUsers(){
        return userService.findAllUser();
    }

    @GetMapping("/findUsersByPage")
    public IPage<User> findUsersByPage(@RequestParam Integer currentPage, Integer pageSize){
        currentPage = currentPage == 0 ? 1 : currentPage;
        pageSize = pageSize == 0 ? 2 : pageSize;
        IPage<User> list = userService.findUsersByPage(currentPage,pageSize);
        return list;
    }

    @PostMapping("/addUser")
    public Map<String,Object> addUser(User user){
        HashMap<String,Object> maps = null;
        try{
            maps = new HashMap<>();
            String result = userService.addUser(user);
            maps.put("result",result);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return maps;
    }


}
