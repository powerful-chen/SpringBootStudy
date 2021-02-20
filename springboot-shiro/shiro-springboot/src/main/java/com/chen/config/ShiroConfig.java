package com.chen.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 10:27
 */
@Configuration
public class ShiroConfig {

    //getShiroFilterFactoryBean 3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro 的内置过滤器
        /*
            anon:无需认证就可以访问
            authc：必须认证了才能访问
            user：必须拥有 记住我 功能才能访问
            perms：拥有对某个资源的权限才能访问
            role：拥有某个角色权限才能访问
         */
        //filterMap.put("/user/add","authc");
        //filterMap.put("/user/update","authc");
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权,正常的情况下，没有授权会跳到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置没有登录的请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }


    //DefaultWebSecurityManager 2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建 realm 对象，需要自定义类 1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
