package com.chen.mapper;

import com.chen.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 15:56
 */
@Repository
@Mapper
public interface UserMapper {
    User queryUserByName(String name);
}
