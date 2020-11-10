package com.cenmo.mogu.business.config;

import com.cenmo.mogu.business.controller.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Cenmo
 * @Date 2020-11-04 2020/11/4
 */
@Configuration
public class mvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**","/js/**","/img/**");
    }
}
