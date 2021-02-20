package com.chen.swagger.controller;

import com.chen.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 22:06
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello" ;
    }
    //只要我们的接口中，返回值存在实体类，它就会被扫描到Swagger中
    @PostMapping("/user")
    public User user(){
        return new User();
    }

    @ApiOperation("Hello控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    @ApiOperation("Post的测试")
    @PostMapping("/Postt")
    public User Postt(@ApiParam("用户类") User user){

        return user;
    }



}
