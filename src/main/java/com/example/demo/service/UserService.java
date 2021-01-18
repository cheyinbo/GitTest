package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;

import java.util.List;


public interface UserService {

    List<User> findAllUser();

    IPage<User> findUsersByPage(Integer currentPage, Integer pageSize);

    User findUserInfo(String name,String password);

    String addUser(User user);
}
