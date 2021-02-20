package com.chen.controller;

import com.chen.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AsyncController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/18 9:37
 */
@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){
        asyncService.hello();
        return "ok";
    }
}
