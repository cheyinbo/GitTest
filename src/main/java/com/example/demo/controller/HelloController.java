package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiOperation(value = "欢迎文档")
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
