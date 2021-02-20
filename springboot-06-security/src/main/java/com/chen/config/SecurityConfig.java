package com.chen.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/15 9:41
 */
//Aop ：拦截器！
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //权限
    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人才能访问
        //请求的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认会到登录页面,需要开启登录的页面
        //login
        //定制登录页 loginPage("/toLogin");
        http.formLogin().loginPage("/toLogin").usernameParameter("user").passwordParameter("pwd").loginProcessingUrl("/login");
        //注销,退出成功返回"/"
        //防止网站工具:get ,post
        http.csrf().disable();//关闭csrf功能
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能,cookie默认保存两周,自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证,springboot 2.1.x 可以直接使用
    //密码编码:PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据正常应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("xiaochen").password(new BCryptPasswordEncoder().encode("1234")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("1234")).roles("vip1", "vip2", "vip3");
    }
}
