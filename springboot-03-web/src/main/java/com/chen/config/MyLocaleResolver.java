package com.chen.config;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ClassName MyLocaleResolver
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/12 17:27
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //解析请求
        String language = request.getParameter("l");
        System.out.println("==========>" + language);
        Locale locale = Locale.getDefault();//如果没有就使用默认的

        //如果请求链接携带了国际化的参数
        if (!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            //国家、地区
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
