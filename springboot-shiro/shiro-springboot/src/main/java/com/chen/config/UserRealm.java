package com.chen.config;

import com.chen.pojo.User;
import com.chen.service.UserServiceImpl;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 10:27
 */
//自定义的UserRealm  extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user==null){
            return null;
        }
        //密码认证，shiro做~
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
