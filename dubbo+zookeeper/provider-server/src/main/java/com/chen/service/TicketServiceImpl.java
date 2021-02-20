package com.chen.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @ClassName TicketServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/18 19:12
 */
//zookeeper：服务注册与发现
@Service //可以被扫描到，在项目一启动就自动注册到注册中心
@Component//使用了Dubbo后尽量不要使用Service注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "小陈你好鸭";
    }
}
