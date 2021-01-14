package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public List<User> findAllUser() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(userQueryWrapper);
    }

    @Override
    public IPage<User> findUsersByPage(Integer currentPage, Integer pageSize) {
        IPage<User> page = new Page<>(currentPage,pageSize);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        baseMapper.selectList(userQueryWrapper);

        return baseMapper.selectPage(page,userQueryWrapper);
    }

    @Override
    public User findUserInfo(String name,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(User::getName,name)
                .eq(User::getPassword,password);
        User user = baseMapper.selectOne(queryWrapper);
        if(user != null){
            return user;
        }
        return null;
    }

    @Override
    public String addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName,user.getName());
        if(baseMapper.selectOne(queryWrapper) != null){
            baseMapper.insert(user);
            return "添加成功";
        }
        return "用户已存在";
    }
}
