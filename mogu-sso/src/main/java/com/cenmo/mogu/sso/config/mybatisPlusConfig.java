package com.cenmo.mogu.sso.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cenmo
 * @Date 2020-10-11 2020/10/11
 */
@Configuration
@MapperScan("com.cenmo.mogu.mapper")
public class mybatisPlusConfig {


//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

    @Bean
    public MyMetaObjectHandler metaObjectHandler(){
        // 自动填充bean注入，注：需自己写MetaObjectHandler的实习类
        return new MyMetaObjectHandler();
    }
}
