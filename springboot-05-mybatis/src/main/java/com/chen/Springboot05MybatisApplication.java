package com.chen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.chen.mapper.*")
@SpringBootApplication
public class Springboot05MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05MybatisApplication.class, args);
	}

}
