package com.chen.service;

import com.chen.pojo.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 16:00
 */

public interface UserService {
    User queryUserByName(String name);
}
