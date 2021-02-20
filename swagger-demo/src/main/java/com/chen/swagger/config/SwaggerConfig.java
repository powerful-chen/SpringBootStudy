package com.chen.swagger.config;

import com.chen.swagger.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/16 22:13
 */
@Configuration
@EnableSwagger2     //开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }




    //配置了Swagger 的 Docket 的bean实例
    @Bean
    public Docket docket(Environment environment) {
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("小陈")
                .apiInfo(apiInfo())
                .enable(flag)//enable是否启动Swagger，如果为false，则Swagger不能在浏览器中访问
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any()：扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象如RequestMapping.class
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.chen.swagger.controller"))
                //paths 过滤扫描路径
                //.paths(PathSelectors.ant("/chen/**"))
                .build();
    }

    //配置Swagger信息 apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("小陈", "https://www.bilibili.com/", "2417359111@qq.com");
        return new ApiInfo("和黄捷淳老婆幸福的一天"
                , "努力变得更好，拥有健康的身体，还有大房子和大车子，资产过五百万，加油！！！", "v1.0"
                , "https://www.baidu.com"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList());

    }
}
