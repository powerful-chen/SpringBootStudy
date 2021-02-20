package com.chen.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @ClassName ScheduledService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/18 11:49
 */
@Service
public class ScheduledService {

    //cron 表达式
    //秒 分 时 日 月 年 周几
    @Scheduled(cron = "0/2 * * * * ?")
    public void hello(){
        System.out.println("hello,淳淳我爱你");
    }
}
