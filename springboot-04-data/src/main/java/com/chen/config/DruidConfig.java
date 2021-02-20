package com.chen.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/14 17:23
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控：web.xml,ServletRegistrationBean
    //因为 springboot 内置了 Servlet 容器，所以没有 web.xml ,替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录，账号密码配置
        Map<String, String> initParameters = new HashMap<>();

        //添加配置
        initParameters.put("loginUsername","admin");//登录key 是固定的 loginUsername
        initParameters.put("loginPassword","123456");

        //允许谁可以访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }


    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤那些请求呢？
        Map<String, String> initParameters = new HashMap<>();

        //这些东西可以不用统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }
}
