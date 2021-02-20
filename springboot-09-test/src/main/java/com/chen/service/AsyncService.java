package com.chen.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName AsyncService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/18 9:35
 */
@Service
@Async
public class AsyncService {


    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理中");
    }
}
