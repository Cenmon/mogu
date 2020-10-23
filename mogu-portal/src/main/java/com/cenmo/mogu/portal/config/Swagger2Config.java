package com.cenmo.mogu.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2网页：可快速完成接口信息的编写
 * @author Cenmo
 * @date 2020/8/2 16:13
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo()) //设置网页提示信息
                .select() //开启过滤
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*"))) //不包含该网址
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()//开启过滤
//                .paths(Predicates.and(PathSelectors.regex("/admin/.*"))) //包含该网址
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("Cenmo", "http://www.baidu.com", "1131412092@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-课程中心API文档")
                .description("本文档描述了后台管理系统课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("Cenmo", "http://www.baidu.com", "1131412092@qq.com"))
                .build();
    }
}
