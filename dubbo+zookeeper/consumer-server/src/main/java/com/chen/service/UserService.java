package com.chen.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @ClassName User
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/18 19:25
 */
@Service //注入容器
public class UserService {
    @Reference //远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    TicketService ticketService;

    public void buyTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到ticket"+ticket);
    }
}
